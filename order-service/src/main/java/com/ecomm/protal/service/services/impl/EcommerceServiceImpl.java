package com.ecomm.protal.service.services.impl;
import com.ecomm.protal.service.entity.OrdersEntity;
import com.ecomm.protal.service.utils.EcommerceServiceMapping;
import com.razorpay.Order;
import com.ecomm.protal.service.dto.*;
import com.ecomm.protal.service.entity.CustomerEntity;
import com.ecomm.protal.service.entity.OrderItems;
import com.ecomm.protal.service.entity.ShippingAddress;
import com.ecomm.protal.service.repo.CustomerRepository;
import com.ecomm.protal.service.repo.OrderRepository;
import com.ecomm.protal.service.repo.ShippingAddressRepository;
import com.ecomm.protal.service.services.EcommerceService;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
@Slf4j
@RequiredArgsConstructor
public class EcommerceServiceImpl implements EcommerceService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ShippingAddressRepository addressRepository;
    @Value("${razorpay.key}")
    private String razorPayKeyId;
    @Value("${razorpay.secret}")
    private String razorPaySecretKey;
    private RazorpayClient razorpayClient;

    @PostConstruct
    public void init() throws RazorpayException {
        this.razorpayClient = new RazorpayClient(razorPayKeyId, razorPaySecretKey);
    }


    @Override
    public PurchaseRequest saveCustomerDetails(PurchaseDto purchaseDto) {
        log.info("Enter into Purchase Request Method {} with details {}", this.getClass(), purchaseDto.toString());
        CustomerEntity customer = EcommerceServiceMapping.convertToCustomerEntity(purchaseDto.getCustomerDto());
        ShippingAddress address = EcommerceServiceMapping.convertToShippingAddressEntity(purchaseDto.getAddressDto());

        log.debug("Creating order entity from orderDto");
        OrdersEntity orders = EcommerceServiceMapping.convertToOrderEntity(purchaseDto.getOrderDto());
        log.debug("converting orderItems from orderDto to orderItem");
        List<OrderItems> orderItems = purchaseDto.getOrdersItemsDto().stream()
                .map(EcommerceServiceMapping::convertToOrderItem).collect(Collectors.toList());
        log.info(orderItems.toString());
        orders.setOrdersItems(orderItems);

        orders.setOrderStatus("ORDER_PENDING");
        address.setOrders(orderItems);
        address.setCustomer(customer);
        this.customerRepository.save(customer);
        log.warn("orders saved with ORDER_PENDING");

        String orderId = makePayment(EcommerceServiceMapping.convertToOrderDto(orders));
        log.info("Getting the RazorPay OrderId from makePayment Method and save it to ordersEntity {}", orderId);
        orders.setRazorPayPaymentId(orderId);
        this.orderRepository.save(orders);
        log.info("Saving the address entity");
        this.addressRepository.save(address);
        log.info("Saving the address entity");
        return PurchaseRequest.builder()
                .orderId(orderId)
                .customerDetails(customer.getCustomerName())
                .orderTrackingNumber(orders.getOrderTrackingNum())
                .build();


    }

    public String makePayment(OrderDto orderDto) {
        log.info("Enter into makePayment method {} with orderDto details {}", this.getClass(), orderDto.toString());
        try {
            JSONObject invoiceRequest = new JSONObject();
            invoiceRequest.put("amount", orderDto.getTotalPrice() * 100);
            invoiceRequest.put("currency", "INR");
            invoiceRequest.put("receipt", "Rakesh");

            // Create order with Razorpay
            Order razorpayOrder = razorpayClient.orders.create(invoiceRequest);
            log.warn("razorpay order created {}", razorpayOrder.toString());
             return razorpayOrder.get("id"); // Returning Razorpay order ID
        } catch (RazorpayException e) {
            throw new RuntimeException("Payment initiation failed: " + e.getMessage());
        }
    }



    public boolean handlePaymentVerification(PaymentVerificationRequest request) throws Exception {

        log.info("Entering handlePaymentVerification with request: {}", request);

        // Create a JSONObject with the payment details
        JSONObject jsonParams = new JSONObject();
        jsonParams.put("razorpay_order_id", request.getOrderId());
        jsonParams.put("razorpay_payment_id", request.getPaymentId());
        jsonParams.put("razorpay_signature", request.getSignature());

        // Verify the payment signature
        boolean isSignatureValid = verifyPayment(jsonParams, razorPaySecretKey);
        log.info("paymentID is {}",request.getPaymentId());

        if (isSignatureValid) {
            OrdersEntity ordersEntity = orderRepository.findByRazorPayPaymentId(request.getOrderId());
            System.out.println(request.getOrderId());

            if (ordersEntity != null) {
                ordersEntity.setOrderStatus("PAYMENT_SUCCESS");
                this.orderRepository.save(ordersEntity);
                return true;
            }


        }

        log.warn("Payment verification failed for Razorpay payment ID: {}", request.getPaymentId());
        return false;
    }


        // Utility method for verifying the Razorpay signature
        private boolean verifyPayment(JSONObject params, String secretKey) throws Exception {
            String generatedSignature = HmacSHA256(params.getString("razorpay_order_id") + "|" + params.getString("razorpay_payment_id"), secretKey);
            return generatedSignature.equals(params.getString("razorpay_signature"));
        }

        // Utility method to generate HMAC SHA256 signature
        private String HmacSHA256(String data, String secret) throws Exception {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
            sha256_HMAC.init(secret_key);
            return Hex.encodeHexString(sha256_HMAC.doFinal(data.getBytes()));
        }






}

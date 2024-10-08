package com.ecomm.protal.service.services.impl;
import com.ecomm.protal.service.entity.OrdersEntity;
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
import com.razorpay.Utils;
import jakarta.annotation.PostConstruct;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class EcommerceServiceImpl implements EcommerceService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ShippingAddressRepository addressRepository;
    @Value("${razorpay.key}")
    private String razorPayKeyId;
    @Value("${razorpay.secret}")
    private String razorPaySecretKey;
    private RazorpayClient razorpayClient;
    @PostConstruct
    public void init() throws RazorpayException {
        this.razorpayClient= new RazorpayClient(razorPayKeyId,razorPaySecretKey);
    }


    @Override
    public PurchaseRequest saveCustomerDetails(PurchaseDto purchaseDto) {
        ShippingAddress shippingAddress = convertToShippingAddress(purchaseDto.getAddressDto());
        // Create the Customer entity from CustomerDto
        CustomerEntity customer = convertToCustomerEntity(purchaseDto.getCustomerDto());
        ShippingAddress address = convertToShippingAddress(purchaseDto.getAddressDto());
        // Create the Order entity from OrderDto
        OrdersEntity orders = convertToOrderEntity(purchaseDto.getOrderDto());
        List<OrderItems> orderItems = purchaseDto.getOrderDto().getOrdersItems().stream()
                .map(this::convertToOrderItem).collect(Collectors.toList());
        orders.setOrdersItems(orderItems);
        address.setOrders(orderItems);
        this.customerRepository.save(customer);
        // Trigger Razorpay payment

        String orderId = makePayment(convertToOrderDto(orders));
        orders.setRazorPayPaymentId(orderId);
        this.orderRepository.save(orders);

        this.addressRepository.save(address);
        return PurchaseRequest.builder()
                .customerDetails(customer.getCustomerName())
                .orderStatus(orders.getOrderStatus())
                .build();


    }
    public String makePayment(OrderDto orderDto) {
        try {
            JSONObject invoiceRequest = new JSONObject();
            invoiceRequest.put("amount", orderDto.getTotalPrice() * 100); // Amount in paise
            invoiceRequest.put("currency", "INR");
            invoiceRequest.put("receipt", orderDto.getRazorPayPaymentId());

            // Create order with Razorpay
            Order razorpayOrder = razorpayClient.orders.create(invoiceRequest);
            return razorpayOrder.get("id"); // Return Razorpay order ID
        } catch (RazorpayException e) {
            throw new RuntimeException("Payment initiation failed: " + e.getMessage());
        }
    }

    public boolean verifyPayment(JSONObject params, String secretKey) throws RazorpayException {
        // Implement your signature verification logic
        return Utils.verifyPaymentSignature(params, secretKey);
    }

    public void handlePaymentVerification(PaymentVerificationRequest request) throws RazorpayException {
        JSONObject jsonParams = new JSONObject();
        jsonParams.put("razorpay_order_id", request.getRazorpayOrderId());
        jsonParams.put("razorpay_payment_id", request.getRazorpayPaymentId());
        jsonParams.put("razorpay_signature", request.getRazorpaySignature());

        boolean isSignatureValid = verifyPayment(jsonParams, "YOUR_SECRET_KEY");

        if (isSignatureValid) {
            // Update order status to PAYMENT_SUCCESS
            OrdersEntity order = orderRepository.findByRazorPayPaymentId(request.getRazorpayOrderId());
            order.setRazorPayPaymentId(order.getRazorPayPaymentId());
            order.setOrderStatus("PAYMENT_SUCCESS");
            orderRepository.save(order);
        } else {
            // Update order status to PAYMENT_FAIL
            OrdersEntity order = orderRepository.findByRazorPayPaymentId(request.getRazorpayOrderId());
            order.setRazorPayPaymentId(order.getRazorPayPaymentId());
            order.setOrderStatus("PAYMENT_FAIL");
            orderRepository.save(order);
        }
    }






    // Method to convert ShippingAddressDto to ShippingAddress entity
    private ShippingAddress convertToShippingAddress(ShippingAddressDto addressDto) {
        ShippingAddress address = new ShippingAddress();
        address.setStreet(addressDto.getStreet());
        address.setCity(addressDto.getCity());
        address.setState(addressDto.getState());
        address.setCountry(addressDto.getCountry());
        address.setZipcode(addressDto.getZipcode());
        address.setHouseNumber(addressDto.getHouseNumber());
        // Set other properties if needed
        return address;
    }
    // Method to convert CustomerDto to CustomerEntity
    private CustomerEntity convertToCustomerEntity(CustomerDto customerDto) {
        CustomerEntity customer = new CustomerEntity();
        customer.setCustomerName(customerDto.getCustomerName());
        customer.setEmail(customerDto.getEmail());
        customer.setPhoneNumber(customerDto.getPhoneNumber());
        customer.setPassword("12345555");
        return customer;
    }
    private OrdersEntity convertToOrderEntity(OrderDto orderDto) {
        OrdersEntity order = new OrdersEntity();
        order.setOrderTrackingNum(orderDto.getOrderTrackingNum());
        order.setTotalQuantity(orderDto.getTotalQuantity());
        order.setTotalPrice(orderDto.getTotalPrice());
        order.setOrderStatus(orderDto.getOrderStatus());
        order.setDateCreated(orderDto.getDateCreated());
        order.setLastUpdated(orderDto.getLastUpdated());
        // Set other properties if needed
        return order;
    }
    private OrderDto convertToOrderDto(OrdersEntity order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderTrackingNum(order.getOrderTrackingNum());
        orderDto.setTotalQuantity(order.getTotalQuantity());
        orderDto.setTotalPrice(order.getTotalPrice());
        orderDto.setDateCreated(order.getDateCreated());
        orderDto.setLastUpdated(order.getLastUpdated());

        // Set other properties if needed
        return orderDto;
    }
    private OrderItems convertToOrderItem(OrderItemDto orderItemDto) {
        return OrderItems.builder()
                .imageUrl(orderItemDto.getImageUrl())
                .unitPrice(orderItemDto.getUnitPrice())
                .quantity(orderItemDto.getQuantity())
                .productId(orderItemDto.getProductId())
                .build();

    }
}

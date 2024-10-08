package com.ecomm.protal.service.services;

import com.ecomm.protal.service.dto.OrderDto;
import com.ecomm.protal.service.dto.PaymentVerificationRequest;
import com.ecomm.protal.service.dto.PurchaseDto;
import com.ecomm.protal.service.dto.PurchaseRequest;
import com.ecomm.protal.service.entity.CustomerEntity;
import com.ecomm.protal.service.entity.ShippingAddress;
import com.razorpay.RazorpayException;

public interface EcommerceService {
    public PurchaseRequest saveCustomerDetails(PurchaseDto purchaseDto);
    public void handlePaymentVerification(PaymentVerificationRequest paymentVerificationRequest) throws RazorpayException;

}

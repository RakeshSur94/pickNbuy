package com.ecomm.protal.service.api;

import com.ecomm.protal.service.dto.*;
import com.ecomm.protal.service.services.EcommerceService;
import com.razorpay.RazorpayException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.binary.Hex;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

@RestController
@RequestMapping("/ecomm")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
public class EcommerceApi {

    private final EcommerceService ecommerceService;


    @PostMapping("/customer")
    public ResponseEntity<?> saveCustomer(@RequestBody PurchaseDto purchaseDto) throws Exception {
        PurchaseRequest purchaseRequest = this.ecommerceService.saveCustomerDetails(purchaseDto);
        return new ResponseEntity<PurchaseRequest>(purchaseRequest, HttpStatus.CREATED);
    }
    @PostMapping("/verify")
    public ResponseEntity<?>verifyPayment(@RequestBody PaymentVerificationRequest request) throws Exception {
        try {
            boolean isVerified = ecommerceService.handlePaymentVerification(request);
            if (isVerified) {
                return ResponseEntity.status(HttpStatus.CREATED).body(PaymentVerificationResponse
                        .builder()
                        .statusCode(HttpStatus.CREATED.getReasonPhrase()).build());
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        PaymentVerificationResponse.builder()
                                .statusCode(HttpStatus.BAD_GATEWAY.getReasonPhrase())
                                .build());
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    PaymentVerificationResponse.builder()
                            .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                            .build());
        }
    }
    }






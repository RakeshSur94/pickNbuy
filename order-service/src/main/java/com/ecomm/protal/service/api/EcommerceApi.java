package com.ecomm.protal.service.api;

import com.ecomm.protal.service.dto.OrderDto;
import com.ecomm.protal.service.dto.PaymentVerificationRequest;
import com.ecomm.protal.service.dto.PurchaseDto;
import com.ecomm.protal.service.dto.PurchaseRequest;
import com.ecomm.protal.service.services.EcommerceService;
import com.razorpay.RazorpayException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ecomm")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
public class EcommerceApi {

    private final EcommerceService ecommerceService;


    @PostMapping("/customer")
    public ResponseEntity<?> saveCustomer(@RequestBody PurchaseDto purchaseDto) throws Exception {
        return new ResponseEntity<PurchaseRequest>(this.ecommerceService.saveCustomerDetails(purchaseDto), HttpStatus.CREATED);
    }
    @PostMapping("/verify")
    public ResponseEntity<String> verifyPayment(@RequestBody PaymentVerificationRequest request) throws RazorpayException {
        ecommerceService.handlePaymentVerification(request);
        return ResponseEntity.ok("Payment verification completed successfully.");
    }


}

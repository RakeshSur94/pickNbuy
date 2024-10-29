package com.ecomm.protal.user.service.api;

import com.ecomm.protal.user.service.dto.*;
import com.ecomm.protal.user.service.services.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
public class CustomerApi {

    private final CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<RegistrationDto> registerCustomer(@RequestBody RegistrationDto registrationDto){
        RegistrationDto register = this.customerService.registerCustomer(registrationDto);
        return new ResponseEntity<RegistrationDto>(register, HttpStatus.CREATED);
    }
    @PutMapping("/update")
    public ResponseEntity<Boolean> updatePassword(@RequestBody ResetPasswordDto resetPasswordDto){
        Boolean result = this.customerService.resetPassword(resetPasswordDto);
        return ResponseEntity.ok(true);
    }
    @PostMapping("/login")
    public ResponseEntity<CustomerDto> login(@RequestBody LoginDto loginDto){
        CustomerDto login = this.customerService.login(loginDto);
        return new ResponseEntity<CustomerDto>(login,HttpStatus.OK);
    }
    @PostMapping("/forget")
    public ResponseEntity<?> resetPassword(@RequestBody ForgetPassword forgetPassword){
        Boolean result = this.customerService.forgetPassword(forgetPassword);
        return ResponseEntity.ok(result);
    }
}

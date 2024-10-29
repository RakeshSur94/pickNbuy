package com.ecomm.protal.user.service.services.impl;

import com.ecomm.protal.user.service.dto.*;
import com.ecomm.protal.user.service.entity.CustomerEntity;
import com.ecomm.protal.user.service.handler.exception.DuplicateEmailException;
import com.ecomm.protal.user.service.handler.exception.EmailOrPasswordNotCorrectException;
import com.ecomm.protal.user.service.mail.EmailSender;
import com.ecomm.protal.user.service.mail.GenerateRandomPassword;
import com.ecomm.protal.user.service.repo.CustomerRepository;
import com.ecomm.protal.user.service.services.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final EmailSender emailSender;
    private final GenerateRandomPassword generateRandomPassword;
    @Override
    public RegistrationDto registerCustomer(RegistrationDto registrationDto) {
        Optional<CustomerEntity> c = this.customerRepository.findByEmail(registrationDto.getEmail());
        if(c.isPresent()){
            throw new DuplicateEmailException("Email is already activate");

        }

        CustomerEntity customer = CustomerEntity.builder()
                .customerName(registrationDto.getCustomerName())
                .email(registrationDto.getEmail())
                .password(registrationDto.getPassword())
                .phoneNumber(registrationDto.getPhoneNumber())
                .build();
        this.customerRepository.save(customer);


        return RegistrationDto.builder()
                .customerName(customer.getCustomerName())
                .email(customer.getEmail())
                .phoneNumber(customer.getPhoneNumber())
                .build();
    }
    @Override
    public CustomerDto login(LoginDto loginDto){
        CustomerEntity customer = this.customerRepository.findByEmailAndPassword(loginDto.getEmail(),loginDto.getPassword());
        if(customer != null){
            CustomerDto customerDto = new CustomerDto();
            BeanUtils.copyProperties(customer,customerDto);
            return customerDto;
        }
        throw new EmailOrPasswordNotCorrectException("Email or password not correct");

    }

    @Override
    public Boolean resetPassword(ResetPasswordDto resetPasswordDto) {
        Optional<CustomerEntity> c = this.customerRepository.findByEmail(resetPasswordDto.getEmail());
        if(c.isPresent()){
            CustomerEntity customer = c.get();
            customer.setPassword(resetPasswordDto.getConfirmPassword());
            this.customerRepository.save(customer);
            return true;

        }
        return false;
    }
    public Boolean forgetPassword(ForgetPassword forgetPassword){
        Optional<CustomerEntity> c = this.customerRepository.findByEmail(forgetPassword.getEmail());
        if(c.isPresent()){
            CustomerEntity customer = c.get();
             String password = this.generateRandomPassword.generatePassword(customer.getEmail());
             this.emailSender.sendOtpEmail(customer.getEmail(), password);
             return true;
        }
        return false;
    }

}

package com.ecomm.protal.user.service.services;

import com.ecomm.protal.user.service.dto.*;

public interface CustomerService {
    public RegistrationDto registerCustomer(RegistrationDto registrationDto);
    public CustomerDto login(LoginDto loginDto);
    public Boolean resetPassword(ResetPasswordDto resetPasswordDto);
    public Boolean forgetPassword(ForgetPassword forgetPassword);

}

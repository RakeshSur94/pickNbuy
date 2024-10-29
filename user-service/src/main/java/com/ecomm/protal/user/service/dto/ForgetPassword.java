package com.ecomm.protal.user.service.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ForgetPassword {
    private String email;
    private String password;
    private String newPassword;
    private String confirmPassword;
}

package com.ecomm.protal.user.service.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ResetPasswordDto {
    private String email;
    private String password;
    private String confirmPassword;
}

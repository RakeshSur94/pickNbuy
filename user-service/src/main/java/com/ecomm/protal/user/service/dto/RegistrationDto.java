package com.ecomm.protal.user.service.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RegistrationDto {
    private String customerName;
    private String email;
    private String phoneNumber;
    private String password;

}

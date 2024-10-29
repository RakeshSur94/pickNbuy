package com.ecomm.protal.user.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDto {
    private List<String> error_messages; //jakarta validation annotations
    private String exception_message;
    private String statusCodeDescription;
    private LocalDateTime timestamp;
    private CustomerDto customerDto;
}

package com.ecomm.protal.user.service.handler;

import com.ecomm.protal.user.service.dto.ResponseDto;
import com.ecomm.protal.user.service.handler.exception.DuplicateEmailException;
import com.ecomm.protal.user.service.handler.exception.EmailNotFoundException;
import com.ecomm.protal.user.service.handler.exception.EmailOrPasswordNotCorrectException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto> methodArgumentNotValidException(MethodArgumentNotValidException e){
        return new ResponseEntity<ResponseDto>(ResponseDto.builder()
                .exception_message(e.getMessage())
                .error_messages(e.getBindingResult().getAllErrors()
                        .stream().map(ObjectError::getObjectName).toList())
                .statusCodeDescription(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .timestamp(LocalDateTime.now()).build(),HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(EmailNotFoundException.class)
    public ResponseEntity<ResponseDto> emailNotFoundException(EmailNotFoundException e){
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseDto.builder()
                .exception_message(e.getMessage())
                .statusCodeDescription(HttpStatus.NOT_FOUND.getReasonPhrase())
                .timestamp(LocalDateTime.now())
                .build());

    }
    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<ResponseDto> duplicateEmailException(DuplicateEmailException e){
        return  ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(ResponseDto.builder()
                .exception_message(e.getMessage())
                .statusCodeDescription(HttpStatus.NOT_ACCEPTABLE.getReasonPhrase())
                .timestamp(LocalDateTime.now())
                .build());

    }
    @ExceptionHandler(EmailOrPasswordNotCorrectException.class)
    public ResponseEntity<ResponseDto> emailOrPasswordNotCorrectException(EmailOrPasswordNotCorrectException e){
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseDto.builder()
                .exception_message(e.getMessage())
                .statusCodeDescription(HttpStatus.NOT_FOUND.getReasonPhrase())
                .timestamp(LocalDateTime.now())
                .build());

    }


}

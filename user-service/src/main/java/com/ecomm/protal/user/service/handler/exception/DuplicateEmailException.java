package com.ecomm.protal.user.service.handler.exception;

public class DuplicateEmailException extends RuntimeException{
    String message;
    public DuplicateEmailException(){

    }
    public DuplicateEmailException(String message){
        super(message);
    }
}

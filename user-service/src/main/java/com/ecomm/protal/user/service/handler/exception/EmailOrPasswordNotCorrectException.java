package com.ecomm.protal.user.service.handler.exception;

public class EmailOrPasswordNotCorrectException extends RuntimeException{
    String message;
    public EmailOrPasswordNotCorrectException(){

    }
    public EmailOrPasswordNotCorrectException(String message){
        super(message);
    }
}

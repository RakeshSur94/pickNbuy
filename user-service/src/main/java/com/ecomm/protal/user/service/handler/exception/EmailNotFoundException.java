package com.ecomm.protal.user.service.handler.exception;

public class EmailNotFoundException extends RuntimeException {
    String message;
   public EmailNotFoundException(){

    }
    public EmailNotFoundException(String message){
       super(message);
    }
}

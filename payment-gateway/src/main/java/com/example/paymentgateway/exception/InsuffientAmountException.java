package com.example.paymentgateway.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InsuffientAmountException extends ResponseStatusException {

     public InsuffientAmountException(){
         super(HttpStatus.BAD_REQUEST,"InsuffientAmount");
     }
}

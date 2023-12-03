package com.baum.canteenApp.exception;

public class AddToCartNotFoundException extends RuntimeException{
    public AddToCartNotFoundException(String message) {
        super(message);
    }
}

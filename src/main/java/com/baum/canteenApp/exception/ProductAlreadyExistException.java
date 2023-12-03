package com.baum.canteenApp.exception;

public class ProductAlreadyExistException extends RuntimeException{
    public ProductAlreadyExistException(String message) {
        super(message);
    }
}

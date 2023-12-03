package com.baum.canteenApp.exception;

public class CustomerEmailAlreadyExistException extends RuntimeException{

    public CustomerEmailAlreadyExistException(String message) {
        super(message);
    }
}

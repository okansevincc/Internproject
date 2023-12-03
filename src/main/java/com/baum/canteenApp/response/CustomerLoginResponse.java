package com.baum.canteenApp.response;

public record CustomerLoginResponse(String token, CustomerResponse customerResponse) {
}

package com.baum.canteenApp.converter;

import com.baum.canteenApp.response.CustomerLoginResponse;
import com.baum.canteenApp.response.CustomerResponse;
import org.springframework.stereotype.Component;

@Component
public class CustomerLoginConverter {


    public CustomerLoginResponse convert(String token, CustomerResponse customerResponse){
        return new CustomerLoginResponse(token, customerResponse);
    }
}

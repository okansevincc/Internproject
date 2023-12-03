package com.baum.canteenApp.converter;

import com.baum.canteenApp.model.Cart;
import com.baum.canteenApp.response.CartResponse;
import org.springframework.stereotype.Component;

@Component
public class CartConverter {

    public CartResponse convert(Cart from){
        return new CartResponse(from.getCartId());
    }
}

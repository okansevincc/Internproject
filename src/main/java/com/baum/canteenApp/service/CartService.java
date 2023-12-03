package com.baum.canteenApp.service;

import com.baum.canteenApp.constant.Constant;
import com.baum.canteenApp.exception.CartNotFoundException;
import com.baum.canteenApp.model.Cart;
import com.baum.canteenApp.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    protected Cart create(){
        Cart cart = new Cart();
        return cartRepository.save(cart);
    }

    protected Cart findById(String cartId){
        return cartRepository.findById(cartId).
                orElseThrow(() -> new CartNotFoundException(Constant.CART_NOT_FOUND));
    }
}

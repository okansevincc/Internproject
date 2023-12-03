package com.baum.canteenApp.converter;

import com.baum.canteenApp.model.AddToCart;
import com.baum.canteenApp.response.AddToCartResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AddToCartConverter {

    private final CartConverter cartConverter;
    private final ProductWithCustomerConverter productWithCustomerConverter;

    public AddToCartResponse convert(AddToCart from){

        return new AddToCartResponse
                (
                        from.getAddToCartId(),
                        cartConverter.convert(from.getCart()),
                        productWithCustomerConverter.convert(from.getProductWithCustomer())
                );
    }
    public List<AddToCartResponse> convert(List<AddToCart> fromList){

        return fromList.stream().map(addToCart -> new AddToCartResponse
                (
                        addToCart.getAddToCartId(),
                        cartConverter.convert(addToCart.getCart()),
                        productWithCustomerConverter.convert(addToCart.getProductWithCustomer())
                )).toList();
    }
}

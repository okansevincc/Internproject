package com.baum.canteenApp.repository;

import com.baum.canteenApp.model.AddToCart;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AddToCartRepository extends MongoRepository<AddToCart,String> {

    List<AddToCart> findAddToCartByCart_CartId(String cartId);

    //List<AddToCart> findAddToCartByCart_CartIdAndProduct_ProductId(String cartId, String productId);

}

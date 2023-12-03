package com.baum.canteenApp.repository;

import com.baum.canteenApp.model.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface CartRepository extends MongoRepository<Cart,String> {


}

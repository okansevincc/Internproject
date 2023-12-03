package com.baum.canteenApp.repository;

import com.baum.canteenApp.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product,String> {
    Optional<Product> findProductByProductName(String productName);

    List<Product> findProductsByProductIdIn (List<String> productIdList);

    List<Product> findProductByCategory_CategoryId(String categoryId);

}

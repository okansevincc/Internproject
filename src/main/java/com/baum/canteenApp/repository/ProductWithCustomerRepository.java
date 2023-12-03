package com.baum.canteenApp.repository;

import com.baum.canteenApp.model.Customer;
import com.baum.canteenApp.model.ProductWithCustomer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductWithCustomerRepository extends MongoRepository<ProductWithCustomer,String> {
    List<ProductWithCustomer> findProductWithCustomerByCustomer(Customer customer);
    
}

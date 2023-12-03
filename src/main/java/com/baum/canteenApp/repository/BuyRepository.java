package com.baum.canteenApp.repository;

import com.baum.canteenApp.model.Buy;
import com.baum.canteenApp.model.Customer;
import com.baum.canteenApp.model.ProductWithCustomer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BuyRepository extends MongoRepository<Buy,String> {

    List<Buy> findBuyByCustomer(Customer customer);

    List<Buy> findBuyByProductsWithCustomers_ProductWithCustomerId(ProductWithCustomer productWithCustomer);



}

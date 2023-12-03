package com.baum.canteenApp.repository;

import com.baum.canteenApp.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CustomerRepository extends MongoRepository<Customer,String> {

    Optional<Customer> findCustomerByEmail(String email);
}

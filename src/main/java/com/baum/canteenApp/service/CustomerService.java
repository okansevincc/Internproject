package com.baum.canteenApp.service;

import com.baum.canteenApp.constant.Constant;
import com.baum.canteenApp.converter.CustomerConverter;
import com.baum.canteenApp.exception.CustomerEmailAlreadyExistException;
import com.baum.canteenApp.exception.CustomerNotFoundException;
import com.baum.canteenApp.model.Customer;
import com.baum.canteenApp.model.Role;
import com.baum.canteenApp.repository.CustomerRepository;
import com.baum.canteenApp.request.CreateCustomerRequest;
import com.baum.canteenApp.request.CustomerJson;
import com.baum.canteenApp.response.CustomerResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final WalletService walletService;
    private final CartService cartService;
    private final CustomerConverter customerConverter;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    String jsonFilePath = "src/main/resources/excel-to-json (1).json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    private void readJsonFile() throws IOException {


        String jsonData = new String(Files.readAllBytes(Paths.get(jsonFilePath)));

        CustomerJson[] customers = objectMapper.readValue(jsonData, CustomerJson[].class);

        for (CustomerJson customer : customers) {
            create(customer);

        }

    }

    private Customer create(CustomerJson customerJson) {

        Customer customer = new Customer
                (
                        customerJson.getUserId(),
                        Role.USER,
                        customerJson.getFirstName(),
                        customerJson.getLastName(),
                        walletService.create(),
                        cartService.create()
                );

        return customerRepository.save(customer);
    }
    public CustomerResponse createCustomer(CreateCustomerRequest createCustomerRequest){

        Optional<Customer> existCustomer = customerRepository.findById(createCustomerRequest.userId());
        if (existCustomer.isPresent()){
            throw new CustomerEmailAlreadyExistException(Constant.CUSTOMER_EMAIL_ALREADY_EXIST);
        }

        Customer customer = new Customer
                (
                        createCustomerRequest.userId(),
                        Role.USER,
                        createCustomerRequest.firstName(),
                        createCustomerRequest.lastName(),
                        walletService.create(),
                        cartService.create()
                );

        return customerConverter.convert(customerRepository.save(customer));
    }

    public CustomerResponse findBCustomerCustomerId(String customerId) {
        Customer customer = customerRepository.findById(customerId).
                orElseThrow(() -> new CustomerNotFoundException(Constant.CUSTOMER_NOT_FOUND));

        return customerConverter.convert(customer);
    }

    public List<CustomerResponse> findAllCustomers() {
        return customerConverter.convert(customerRepository.findAll());
    }

    public Customer findByCustomerId(String customerId) {
        return customerRepository.findById(customerId).
                orElseThrow(() -> new CustomerNotFoundException(Constant.CUSTOMER_NOT_FOUND));
    }

    protected Customer findByEmail(String email) {
        return customerRepository.findCustomerByEmail(email).
                orElseThrow(() -> new CustomerNotFoundException(Constant.CUSTOMER_NOT_FOUND));
    }
}

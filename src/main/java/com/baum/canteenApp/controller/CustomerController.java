package com.baum.canteenApp.controller;

import com.baum.canteenApp.request.CreateCustomerRequest;
import com.baum.canteenApp.request.CustomerJson;
import com.baum.canteenApp.response.CustomerResponse;
import com.baum.canteenApp.service.CustomerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerResponse> create(@RequestBody @Valid CreateCustomerRequest createCustomerRequest){
        return new ResponseEntity<>(customerService.createCustomer(createCustomerRequest),HttpStatus.CREATED);
    }

//    @GetMapping("/get")
//    public void readJsonFile() throws IOException {
//        customerService.readJsonFile();
//    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerResponse> findCustomerByCustomerId(@PathVariable @NotBlank String customerId){
        return new ResponseEntity<>(customerService.findBCustomerCustomerId(customerId),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> findAllCustomers(){
        return new ResponseEntity<>(customerService.findAllCustomers(),HttpStatus.OK);
    }
}

package com.baum.canteenApp.controller;


import com.baum.canteenApp.request.CreateAddToCartRequest;
import com.baum.canteenApp.response.AddToCartResponse;
import com.baum.canteenApp.service.AddToCartService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/addToCarts")
@RequiredArgsConstructor
public class AddToCartController {


    private final AddToCartService addToCartService;

    @PostMapping
    public ResponseEntity<AddToCartResponse> create(@RequestBody @Valid CreateAddToCartRequest createAddToCartRequest){
        return new ResponseEntity<>(addToCartService.create(createAddToCartRequest),HttpStatus.CREATED);
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<List<AddToCartResponse>> getAllProductInCart(@PathVariable @NotBlank String cartId){
        return new ResponseEntity<>(addToCartService.findAllByCartId(cartId),HttpStatus.OK);
    }

    @DeleteMapping("/deleteSingleProductFromCart/{addToCartId}")
    public ResponseEntity<Void> deleteProductFromCart(@PathVariable @NotBlank String addToCartId){
        addToCartService.deleteProductFromCart(addToCartId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

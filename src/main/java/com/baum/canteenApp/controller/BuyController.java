package com.baum.canteenApp.controller;

import com.baum.canteenApp.request.CreateBuyRequest;
import com.baum.canteenApp.response.BuyResponse;
import com.baum.canteenApp.service.BuyService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/buys")
public class BuyController {

    private final BuyService buyService;

    @PostMapping
    public ResponseEntity<Void> create (@RequestBody  @Valid CreateBuyRequest createBuyRequest){
        buyService.buyFromCart(createBuyRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<List<BuyResponse>> findBuyByCustomerId(@PathVariable @NotBlank String customerId){
        return new ResponseEntity<>(buyService.findBuyByCustomerId(customerId), HttpStatus.OK);
    }

    @GetMapping("/findAllBuys")
    public ResponseEntity<List<BuyResponse>> findAllBuys(){
        return new ResponseEntity<>(buyService.findAllBuys(), HttpStatus.OK);
    }
}

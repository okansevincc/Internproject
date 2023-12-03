package com.baum.canteenApp.controller;


import com.baum.canteenApp.request.AddBalanceRequest;
import com.baum.canteenApp.response.WalletResponse;
import com.baum.canteenApp.service.WalletService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/wallets")
@RequiredArgsConstructor
public class WalletController {


    private final WalletService walletService;

    @PutMapping("/{walletId}")
    public ResponseEntity<WalletResponse> addBalance(@PathVariable @NotBlank String walletId,
                                                     @RequestBody  AddBalanceRequest addBalanceRequest){
        return new ResponseEntity<>(walletService.addBalance(walletId,addBalanceRequest), HttpStatus.OK);
    }
}

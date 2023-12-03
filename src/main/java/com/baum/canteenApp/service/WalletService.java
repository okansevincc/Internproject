package com.baum.canteenApp.service;

import com.baum.canteenApp.constant.Constant;
import com.baum.canteenApp.converter.WalletConverter;
import com.baum.canteenApp.exception.WalletNotFoundException;
import com.baum.canteenApp.model.Wallet;
import com.baum.canteenApp.repository.WalletRepository;
import com.baum.canteenApp.request.AddBalanceRequest;
import com.baum.canteenApp.response.WalletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletService {

    private final WalletRepository walletRepository;
    private final WalletConverter walletConverter;

    public WalletResponse addBalance(String walletId, AddBalanceRequest addBalanceRequest){
        Wallet wallet = findById(walletId);
        Double totalBalance = wallet.getBalance() + addBalanceRequest.newBalance();
        wallet.setBalance(totalBalance);

        return walletConverter.convert(walletRepository.save(wallet));
    }

    protected void walletControl(double totalPrice,String walletId){
        Wallet wallet = findById(walletId);
        double newBalance = wallet.getBalance() - totalPrice;
        wallet.setBalance(newBalance);
        walletRepository.save(wallet);
    }

    protected Wallet create(){
        Wallet wallet = new Wallet();
        return walletRepository.save(wallet);
    }

    protected Wallet findById(String walletId){
        return walletRepository.findById(walletId).
                orElseThrow(() -> new WalletNotFoundException(Constant.WALLET_NOT_FOUND));
    }
}

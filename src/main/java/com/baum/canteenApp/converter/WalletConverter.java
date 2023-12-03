package com.baum.canteenApp.converter;

import com.baum.canteenApp.model.Wallet;
import com.baum.canteenApp.response.WalletResponse;
import org.springframework.stereotype.Component;

@Component
public class WalletConverter {

    public WalletResponse convert(Wallet from){
        return new WalletResponse(from.getWalletId(), from.getBalance());
    }

}

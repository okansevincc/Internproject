package com.baum.canteenApp.repository;

import com.baum.canteenApp.model.Wallet;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WalletRepository extends MongoRepository<Wallet,String> {

}

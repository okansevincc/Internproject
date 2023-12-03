package com.baum.canteenApp.converter;

import com.baum.canteenApp.model.Customer;
import com.baum.canteenApp.response.CustomerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomerConverter {

    private final WalletConverter walletConverter;
    private final CartConverter cartConverter;

    public CustomerResponse convert(Customer from){
        return new CustomerResponse
                (
                        from.getUserId(),
                        from.getEmail(),
                        from.getFirstName(),
                        from.getLastName(),
                        walletConverter.convert(from.getWallet()),
                        cartConverter.convert(from.getCart())
                );
    }

    public List<CustomerResponse> convert(List<Customer> fromList){
        return fromList.stream().map(customer -> new CustomerResponse
                (
                        customer.getUserId(),
                        customer.getEmail(),
                        customer.getFirstName(),
                        customer.getLastName(),
                        walletConverter.convert(customer.getWallet()),
                        cartConverter.convert(customer.getCart())))
                .toList();
    }
}

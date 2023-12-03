package com.baum.canteenApp.converter;

import com.baum.canteenApp.model.Buy;
import com.baum.canteenApp.response.BuyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BuyConverter {

    private final CustomerConverter customerConverter;
    private final ProductWithCustomerConverter productWithCustomerConverter;

    public BuyResponse convert(Buy from){
        return new BuyResponse(
                from.getBuyId(),
                from.getLocalDateTime(),
                customerConverter.convert(from.getCustomer()),
                productWithCustomerConverter.convert(from.getProductsWithCustomers()));
    }

    public List<BuyResponse> convert(List<Buy> fromList){
        return fromList.stream().map(buy -> new BuyResponse
                (
                        buy.getBuyId(),
                        buy.getLocalDateTime(),
                        customerConverter.convert(buy.getCustomer()),
                        productWithCustomerConverter.convert(buy.getProductsWithCustomers())
                )).toList();
    }
}

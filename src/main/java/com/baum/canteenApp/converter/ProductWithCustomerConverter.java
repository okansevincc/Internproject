package com.baum.canteenApp.converter;

import com.baum.canteenApp.model.ProductWithCustomer;
import com.baum.canteenApp.response.ProductWithCustomerResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductWithCustomerConverter {

    private final CustomerConverter customerConverter;
    private final ProductConverter productConverter;

    public ProductWithCustomerResponse convert(ProductWithCustomer from){
        return new ProductWithCustomerResponse(
                from.getProductWithCustomerId(),
                customerConverter.convert(from.getCustomer()),
                productConverter.convert(from.getProduct()));
    }

    public List<ProductWithCustomerResponse> convert(List<ProductWithCustomer> fromList){
        return fromList.stream().map(productWithCustomer -> new ProductWithCustomerResponse
                (
                        productWithCustomer.getProductWithCustomerId(),
                        customerConverter.convert(productWithCustomer.getCustomer()),
                        productConverter.convert(productWithCustomer.getProduct())
                )).toList();
    }
}
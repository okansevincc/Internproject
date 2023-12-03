package com.baum.canteenApp.converter;

import com.baum.canteenApp.model.Product;
import com.baum.canteenApp.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductConverter {

    private final CategoryConverter categoryConverter;


    public ProductResponse convert(Product from){
        return new ProductResponse
                (
                        from.getProductId(),
                        from.getProductName(),
                        from.getPrice(),
                        from.getUnitInStock(),
                        categoryConverter.convert(from.getCategory())
                );
    }
    public List<ProductResponse> convert(List<Product> fromList){
        return fromList.stream().map(product -> new ProductResponse
                (
                        product.getProductId(),
                        product.getProductName(),
                        product.getPrice(),
                        product.getUnitInStock(),
                        categoryConverter.convert(product.getCategory())
                )).toList();
    }
}

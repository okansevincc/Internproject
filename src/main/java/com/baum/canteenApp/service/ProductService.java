package com.baum.canteenApp.service;

import com.baum.canteenApp.constant.Constant;
import com.baum.canteenApp.converter.ProductConverter;
import com.baum.canteenApp.exception.ProductAlreadyExistException;
import com.baum.canteenApp.exception.ProductNotFoundException;
import com.baum.canteenApp.model.Category;
import com.baum.canteenApp.model.Product;
import com.baum.canteenApp.repository.ProductRepository;
import com.baum.canteenApp.request.CreateProductRequest;
import com.baum.canteenApp.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductConverter productConverter;
    private final CategoryService categoryService;

    public ProductResponse create(CreateProductRequest createProductRequest) {
        Category category = categoryService.findByCategoryId(createProductRequest.categoryId());

        Optional<Product> productByProductName =
                productRepository.findProductByProductName(createProductRequest.productName());

        if (productByProductName.isPresent()) {
            throw new ProductAlreadyExistException(Constant.PRODUCT_ALREADY_EXIST);
        }

        Product product = new Product
                (
                        createProductRequest.productName(),
                        createProductRequest.price(),
                        createProductRequest.unitInStock(),
                        category
                );

        return productConverter.convert(productRepository.save(product));
    }

    public void deleteProductByProductId(String id){
        productRepository.deleteById(id);
    }

    public List<ProductResponse> findAll() {
        return productConverter.convert(productRepository.findAll());
    }

    public ProductResponse findByProductId(String productId) {
        Product product = productRepository.findById(productId).orElseThrow
                (
                        () -> new ProductNotFoundException(Constant.PRODUCT_NOT_FOUND)
                );
        return productConverter.convert(product);
    }


    public ProductResponse addUnitInStock(String productId, Integer newUnitInStock) {
        Product product = findById(productId);
        Integer unitInStock = product.getUnitInStock();
        Integer totalUnitInStock = unitInStock + newUnitInStock;
        product.setUnitInStock(totalUnitInStock);
        return productConverter.convert(productRepository.save(product));
    }

    public List<ProductResponse> findProductByCategory_CategoryId(String categoryId) {
        Category category = categoryService.findByCategoryId(categoryId);
        return productConverter.convert(productRepository.findProductByCategory_CategoryId(category.getCategoryId()));

    }

    protected List<Product> findProductsByProductIdIn(List<String> productIdList) {

        return productRepository.findProductsByProductIdIn(productIdList);
    }

    protected Product findById(String productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(Constant.PRODUCT_NOT_FOUND));
    }

    protected Product findProductByProductIdAndSetUnitInStockForAddProductToCart(String productId) {
        Product product = findById(productId);
        Integer newUnitInStock = product.getUnitInStock() - 1;
        product.setUnitInStock(newUnitInStock);
        return productRepository.save(product);
    }

    protected void findProductByProductIdAndSetUnitInStockForDeleteProductFromCart(String productId) {
        Product product = findById(productId);
        Integer newUnitInStock = product.getUnitInStock() + 1;
        product.setUnitInStock(newUnitInStock);
        productRepository.save(product);
    }
}

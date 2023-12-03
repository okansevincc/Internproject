package com.baum.canteenApp.service;

import com.baum.canteenApp.constant.Constant;
import com.baum.canteenApp.converter.AddToCartConverter;
import com.baum.canteenApp.exception.AddToCartNotFoundException;
import com.baum.canteenApp.model.AddToCart;
import com.baum.canteenApp.model.Cart;
import com.baum.canteenApp.model.Customer;
import com.baum.canteenApp.model.Product;
import com.baum.canteenApp.model.ProductWithCustomer;
import com.baum.canteenApp.repository.AddToCartRepository;
import com.baum.canteenApp.repository.ProductWithCustomerRepository;
import com.baum.canteenApp.request.CreateAddToCartRequest;
import com.baum.canteenApp.response.AddToCartResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class AddToCartService {

    private final AddToCartRepository addToCartRepository;
    private final ProductWithCustomerRepository productWithCustomerRepository;
    private final CartService cartService;
    private final ProductService productService;
    private final CustomerService customerService;
    private final AddToCartConverter addToCartConverter;

    public AddToCartResponse create(CreateAddToCartRequest createAddToCartRequest){

        Cart cart = cartService.findById(createAddToCartRequest.cartId());

        Product product = productService
                .findProductByProductIdAndSetUnitInStockForAddProductToCart(createAddToCartRequest.productId());

        Customer customer = customerService.findByCustomerId(createAddToCartRequest.customerId());

        ProductWithCustomer productWithCustomer = new ProductWithCustomer(product, customer);
        productWithCustomerRepository.save(productWithCustomer);

        AddToCart addToCart = new AddToCart(cart, productWithCustomer);

        return addToCartConverter.convert(addToCartRepository.save(addToCart));

    }

    public List<AddToCartResponse> findAllByCartId(String cartId){

        Cart cart = cartService.findById(cartId);

        List<AddToCart> addToCartList = addToCartRepository.findAddToCartByCart_CartId(cart.getCartId());

        return addToCartConverter.convert(addToCartList);
    }


    public void deleteProductFromCart(String addToCartId){
        AddToCart addToCart = findAddToCartById(addToCartId);
        String productId = addToCart.getProductWithCustomer().getProduct().getProductId();
        productService.findProductByProductIdAndSetUnitInStockForDeleteProductFromCart(productId);
        addToCartRepository.deleteById(addToCart.getAddToCartId());
    }

    protected void deleteAllProductFromCart(String cartId){
        Cart cart = cartService.findById(cartId);
        List<AddToCart> addToCartList = findAddToCartByCart_CartId(cart.getCartId());

        addToCartRepository.deleteAll(addToCartList);
    }

    protected List<AddToCart> findAddToCartByCart_CartId(String cartId){
        Cart cart = cartService.findById(cartId);
        return addToCartRepository.findAddToCartByCart_CartId(cart.getCartId());
    }
    private AddToCart findAddToCartById(String addToCartId){
       return addToCartRepository.findById(addToCartId)
                .orElseThrow(() -> new AddToCartNotFoundException(Constant.ADD_TO_CART_NOT_FOUND));
    }

}

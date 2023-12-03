package com.baum.canteenApp.service;


import com.baum.canteenApp.converter.BuyConverter;
import com.baum.canteenApp.model.*;
import com.baum.canteenApp.repository.BuyRepository;
import com.baum.canteenApp.repository.ProductWithCustomerRepository;
import com.baum.canteenApp.request.CreateBuyRequest;
import com.baum.canteenApp.response.BuyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class BuyService {

    private final BuyRepository buyRepository;
    private final CustomerService customerService;
    private final AddToCartService addToCartService;
    private final WalletService walletService;
    private final BuyConverter buyConverter;
    private final ProductWithCustomerRepository productWithCustomerRepository;

    public void buyFromCart(CreateBuyRequest createBuyRequest){

        Customer customer = customerService.findByCustomerId(createBuyRequest.customerId());

        List<AddToCart> addToCarts = addToCartService.findAddToCartByCart_CartId(customer.getCart().getCartId());

        List<ProductWithCustomer> products = addToCarts.stream().map(AddToCart::getProductWithCustomer).toList();

        for (AddToCart addToCart: addToCarts) {

            walletService.walletControl(addToCart.getProductWithCustomer().getPrice(),addToCart.getProductWithCustomer().getCustomerWalletId());
        }

        Buy buy = new Buy(LocalDateTime.now(),customer,products);

        buyRepository.save(buy);

        addToCartService.deleteAllProductFromCart(customer.getCart().getCartId());

    }
    public List<BuyResponse> findBuyByCustomerId(String customerId){
        Customer customer = customerService.findByCustomerId(customerId);


        List<Buy> buys = buyRepository.findBuyByCustomer(customer);
        List<ProductWithCustomer> productWithCustomers = productWithCustomerRepository.findProductWithCustomerByCustomer(customer);
        Boolean flag;
        for (ProductWithCustomer productWithCustomer:productWithCustomers) {
            for (Buy buy:buyRepository.findBuyByProductsWithCustomers_ProductWithCustomerId(productWithCustomer)){
                flag = false;
                for (Buy buy1:buys) {
                    if (Objects.equals(buy.getBuyId(),buy1.getBuyId())) {
                        flag = true;
                    }
                }
                if (flag == false){
                    buys.add(buy);
                }
            }
        }
        return buyConverter.convert(buys);
    }
    public List<BuyResponse> findAllBuys() {
        return buyConverter.convert(buyRepository.findAll());
    }

}

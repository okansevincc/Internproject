package com.baum.canteenApp.controller;

import com.baum.canteenApp.converter.CustomerConverter;
import com.baum.canteenApp.model.Customer;
import com.baum.canteenApp.response.BuyResponse;
import com.baum.canteenApp.service.BuyService;
import com.baum.canteenApp.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class HomeController {

    private final CustomerService customerService;
    private final CustomerConverter converter;
    private final BuyService buyService;

    @GetMapping("/")
    public RedirectView redirect() {
        return new RedirectView("/home");
    }


    @GetMapping("/home")
    public ResponseEntity<Customer> home(OAuth2AuthenticationToken authenticationToken) {
        String userId = authenticationToken.getPrincipal().getAttribute("sub");
        return new ResponseEntity<>(customerService.findByCustomerId(userId), HttpStatus.OK);
    }

    @GetMapping("/adminpage")
    public ResponseEntity<List<BuyResponse>> buys() {
        return new ResponseEntity<>(buyService.findAllBuys(), HttpStatus.OK);
    }
}

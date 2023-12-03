package com.baum.canteenApp.service;

import com.baum.canteenApp.model.Customer;
import com.baum.canteenApp.model.KantinUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KantinUserService {

    private final CustomerService customerService;


    public KantinUser findUserByIdentityNumber(OidcUser oidcUser) {
        String sub = oidcUser.getAttribute("sub");
        Customer customer = customerService.findByCustomerId(sub);
        return new KantinUser(oidcUser, customer);
    }
}
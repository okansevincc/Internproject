package com.baum.canteenApp.service;

import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

@Service
public class KantinAuthUserService extends OidcUserService {
    private final KantinUserService kantinUserService;
    public KantinAuthUserService(KantinUserService kantinUserService) {
        this.kantinUserService = kantinUserService;
    }

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OidcUser oidcUser = super.loadUser(userRequest);
        return kantinUserService.findUserByIdentityNumber(oidcUser);
    }
}
//Full name
package com.baum.canteenApp.security;

import com.baum.canteenApp.service.KantinAuthUserService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Value("${oidc.clientId}")
    private String clientId;

    @Value("${oidc.clientSecret}")
    private String clientSecret;

    @Value("${oidc.authorizationUri}")
    private String authorizationUri;

    @Value("${oidc.endSessionEndpoint}")
    private String endSessionEndpoint;

    @Value("${oidc.redirectUriTemplate}")
    private String redirectUriTemplate;

    @Value("${oidc.tokenUri}")
    private String tokenUri;

    @Value("${oidc.jwkSetUri}")
    private String jwkSetUri;

    @Value("${oidc.logoutRedirectUri}")
    private String logoutRedirectUri;

    @Autowired
    private KantinAuthUserService kantinAuthUserService;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable).cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/login/**").permitAll();
                    auth.requestMatchers("/api/v1/admins").permitAll();
                    auth.requestMatchers("/api/v1/customers/**").permitAll();
                    auth.requestMatchers("/api/v1/buys/**").permitAll();
                    auth.requestMatchers("/api/v1/addToCarts/**").permitAll();
                    auth.requestMatchers("/api/v1/wallets/**").permitAll();
                    auth.requestMatchers("/api/v1/products/**").permitAll();
                    auth.requestMatchers("/api/v1/categories/**").permitAll();
                    auth.requestMatchers("/api/v1/buyfors/**").permitAll();
                    auth.requestMatchers("/adminpage").hasAnyAuthority("ADMIN");
                    auth.anyRequest().authenticated();
                })
                .oauth2Login(withDefaults())
                .logout(logout -> logout
                        .logoutSuccessHandler(oidcLogoutSuccessHandler())
                )
                .oauth2Login((oauth2Login) -> oauth2Login.userInfoEndpoint(
                        (userInfoEndPoint) -> userInfoEndPoint.oidcUserService(kantinAuthUserService))
                )
                .build();


    }

    @Bean
    public LogoutSuccessHandler oidcLogoutSuccessHandler() {
        OidcClientInitiatedLogoutSuccessHandler oidcLogoutSuccessHandler =
                new OidcClientInitiatedLogoutSuccessHandler(clientRegistrationRepository());

        // Sets the location that the End-User's User Agent will be redirected to
        // after the logout has been performed at the Provider
        oidcLogoutSuccessHandler.setPostLogoutRedirectUri("{baseUrl}");

        return oidcLogoutSuccessHandler;
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(anadoluAuthClientRegistration());
    }


    private ClientRegistration anadoluAuthClientRegistration() {
        Map<String, Object> providerMetadata = new HashMap<>(1);
        providerMetadata.put("end_session_endpoint", endSessionEndpoint);
        return ClientRegistration.withRegistrationId("anadoluauth")
                .clientId(clientId)
                .clientSecret(clientSecret)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_POST)
                .redirectUri(redirectUriTemplate)
                .scope("openid", "offline_access")
                .authorizationUri(authorizationUri)
                .tokenUri(tokenUri)
                .jwkSetUri(jwkSetUri)
                .userNameAttributeName(IdTokenClaimNames.SUB)
                .clientName("AnadoluAuth")
                .providerConfigurationMetadata(providerMetadata)

                .build();
    }
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().
                requestMatchers("/swagger-ui/**", "/v3/api-docs/**");
    }
    @Bean
    public AuthenticationManager authenticationManager(final AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@NotNull CorsRegistry registry) {
                registry.addMapping("/**").allowedMethods("PUT","DELETE","POST","GET").allowedOrigins("*");
            }
        };
    }
}
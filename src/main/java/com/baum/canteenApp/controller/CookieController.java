package com.baum.canteenApp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cookie")
public class CookieController {
    @GetMapping
    public String findBuyByCustomerId(@RequestHeader("Cookie") String cookie) {
        return cookie;
    }
}
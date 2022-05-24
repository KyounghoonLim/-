package com.daki.api.controller;

import com.daki.api.service.OAuthService;
import com.daki.api.service.OAuthServiceImpl;
import com.daki.common.config.TokenDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/oauth")
public class OAuthController {

    //OAuthSErvice oathService; 는 왜 안됌?
    @Autowired
    OAuthServiceImpl oAuthService;

    @GetMapping("/login")
    ResponseEntity<TokenDto> OauthLogin(@RequestHeader(value="token") String token, @RequestHeader String type){
        return oAuthService.OAuthLogin(token, type);
    }


    //로그인 요청
//    @GetMapping("/login")
//    ResponseEntity<TokenDto> OauthLogin(@RequestParam String accessToken, @RequestParam String type){
//        return oAuthService.OAuthLogin(accessToken, type);
//    }



}

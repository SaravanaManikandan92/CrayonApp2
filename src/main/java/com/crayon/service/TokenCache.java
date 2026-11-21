package com.crayon.service;


import com.crayon.model.TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class TokenCache {

    @Autowired
    TokenService tokenService;

    private static HashMap<String,String> tokenMap=new HashMap<>();
    private static HashMap<String,Long> expiryMap=new HashMap<>();


    public synchronized String getToken(String source) {

        if (tokenMap.get(source) == null || System.currentTimeMillis() >= expiryMap.get(source)) {
            refreshToken(source);
        }
        return tokenMap.get(source);

    }

    private  void refreshToken(String source) {

        TokenResponse tokenResponse = fetchTokenFromApi(source);
        if(tokenResponse==null){
            throw new NullPointerException("Something went wrong here.Null value is received as token.");
        }

        String token = tokenResponse.getAccessToken();
        tokenMap.put(source,token);
        long expiryTime = System.currentTimeMillis() + (tokenResponse.getExpiresIn() - 60) * 1000L;
        expiryMap.put(source,expiryTime);
    }

    private TokenResponse fetchTokenFromApi(String source) {

        TokenResponse tokenResponse = tokenService.getAccessToken(source);
        return tokenResponse;
    }
}


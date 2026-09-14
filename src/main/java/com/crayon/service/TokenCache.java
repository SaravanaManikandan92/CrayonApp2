package com.crayon.service;


import com.crayon.model.TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TokenCache {

    @Autowired
    TokenService tokenService;

    private static String token;
    private static long expiryTime;


    public synchronized String getToken(String source) {

        if (token == null || System.currentTimeMillis() >= expiryTime) {
            refreshToken(source);
        }
        return token;

    }

    private  void refreshToken(String source) {

        TokenResponse tokenResponse = fetchTokenFromApi(source);
        if(tokenResponse==null){
            throw new NullPointerException("Something went wrong here.Null value is received as token.");
        }
        token = tokenResponse.getAccessToken();
        expiryTime = System.currentTimeMillis() + (tokenResponse.getExpiresIn() - 60) * 1000L;
    }

    private TokenResponse fetchTokenFromApi(String source) {

        TokenResponse tokenResponse = tokenService.getAccessToken(source);
        return tokenResponse;
    }
}


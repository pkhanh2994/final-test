package com.finaltest.app.dto;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Random;

@Component
public class Utils {

    private final Random RANDOM = new SecureRandom();
    private final String RANDOMSTRING ="0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public String generateUserId(int length){
        return generateRandomString(length);
    }

    private String generateRandomString(int length) {
        StringBuilder returnValue = new StringBuilder(length);
        for(int i=0;i<length;i++){
            returnValue.append(RANDOMSTRING.charAt(RANDOM.nextInt(RANDOMSTRING.length())));
        }
        return new String(returnValue);
    }

}

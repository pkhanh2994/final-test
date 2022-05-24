package com.finaltest.app.exception;

import lombok.AllArgsConstructor;

public class UserServiceException extends RuntimeException{
    private static final long serialVersionUID = 1217940487435258525L;

    public UserServiceException(String message){
        super(message);
    }
}

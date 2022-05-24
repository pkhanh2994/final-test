package com.finaltest.app.security;

import com.finaltest.app.root.SpringApplicationContext;

public class SecurityConstant {

    public static final long EXPIRATION_TIME = 259200000;//3 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL ="/users/*";
    public static final String EXCEL_UPLOAD ="/excel/*";
    public static final String LOGIN_URL = "/users/login";

    public static String getTokenSecret(){
        AppProperties appProperties = (AppProperties) SpringApplicationContext.getBean("appProperties");
        return appProperties.getToken();
    }
}

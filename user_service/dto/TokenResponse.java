package com.swiggylite.user_service.dto;

public class TokenResponse {

    private String token;
    private String tokenType = "Bearer";

    public TokenResponse() {
    }

    public TokenResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public String getTokenType() {
        return tokenType;
    }
}

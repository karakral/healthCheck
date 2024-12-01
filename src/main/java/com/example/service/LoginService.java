package com.example.service;

import com.example.dto.BackofficeLoginRequestDto;
import com.example.dto.TokenResponse;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    public TokenResponse loginService (BackofficeLoginRequestDto backofficeLoginRequestDto) {

        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken("bearer: token");
        return tokenResponse;
    }
}

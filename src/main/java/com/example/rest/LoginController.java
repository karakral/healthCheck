package com.example.rest;


import com.example.dto.BackofficeLoginRequestDto;
import com.example.dto.TokenResponse;
import com.example.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/api/bo/authentication/")
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping("backoffice-login/v1")
    public ResponseEntity<TokenResponse> backofficeLogin(@RequestBody BackofficeLoginRequestDto backofficeLoginRequestDto) throws Exception {
        return ResponseEntity.ok(loginService.loginService(backofficeLoginRequestDto) );
    }
}

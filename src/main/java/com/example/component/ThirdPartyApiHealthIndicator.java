package com.example.component;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class ThirdPartyApiHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        System.out.println("Third party is being invoked!");
        RestTemplate restTemplate = new RestTemplate();

        try {
            // Authenticate to get JWT token
            Map<String, String> loginRequest = new HashMap<>();
            String username = System.getenv("LOGIN_USERNAME");
            String password = System.getenv("LOGIN_PASSWORD");

            loginRequest.put("username", username);
            loginRequest.put("password", password);
            loginRequest.put("captcha", "your_captcha");
            loginRequest.put("captchaKey", "your_captchaKey");

            String jwtToken = restTemplate.postForObject(
                    "localhost:9001/api/bo/authentication/backoffice-login/v1", loginRequest, String.class);

            // Call an endpoint using the token
            String apiResponse = restTemplate.getForObject(
                    "localhost:9001/api/bo/account/api/bo/account/get-account-list/v1" + jwtToken, String.class);

            if (apiResponse != null) {
                return Health.up()
                        .withDetail("thirdPartyApi", "reachable")
                        .build();
            }
        } catch (Exception e) {
            return Health.down()
                    .withDetail("thirdPartyApi", "unreachable")
                    .withDetail("error", e.getMessage())
                    .build();
        }

        return Health.down().build();
    }
}

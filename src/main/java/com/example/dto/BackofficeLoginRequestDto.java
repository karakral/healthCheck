package com.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;


public class BackofficeLoginRequestDto {

    @NotNull(message = "msg.common.error.bad.input.data")
    @JsonProperty
    private String username;

    @NotNull(message = "msg.common.error.bad.input.data")
    @JsonProperty
    private String password;

    @NotNull(message = "msg.common.error.bad.input.data")
    private String captcha;

    @NotNull(message = "msg.common.error.bad.input.data")
    private String captchaKey;

    public String getCaptcha() {
        return captcha.toLowerCase();
    }
}

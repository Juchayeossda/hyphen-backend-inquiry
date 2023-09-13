package com.get.hyphenbackendinquiry.global.util;

import com.get.hyphenbackendinquiry.global.config.webClient.WebClientConfig;
import com.get.hyphenbackendinquiry.global.lib.webClient.dto.response.ValidateResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Getter
public class WebClientUtil {

    private final WebClientConfig webClientConfig;

    @Value(value = "${webClient.servers.userServer.path}")
    private String userServerPath;

    public ValidateResponse validate(String accessToken) {
        return webClientConfig.webClient().method(HttpMethod.POST)
                .uri(userServerPath + "/api/auth/jwt/validate")
                .header("Authorization", "Bearer " + accessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(ValidateResponse.class)
                .block();
    }
}

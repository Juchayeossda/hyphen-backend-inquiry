package com.get.hyphenbackendinquiry.global.util;

import com.get.hyphenbackendinquiry.global.lib.webClient.dto.response.ValidateResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Getter
public class ValidateUtil {

    private final WebClientUtil webClientUtil;

    public ValidateResponse validate(String accessToken) {
        return webClientUtil.validate(accessToken);
    }

    public String getTokenByHeader(String header) {
        return header.replace("Bearer ", "");
    }
}

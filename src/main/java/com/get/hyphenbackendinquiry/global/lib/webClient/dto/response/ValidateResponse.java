package com.get.hyphenbackendinquiry.global.lib.webClient.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ValidateResponse {

    private String uid;
    private String userStatus;
    private String userRole;
}

package com.daki.api.request;

import io.swagger.annotations.ApiModel;
import lombok.Getter;

@ApiModel
@Getter
public class TokenRequestDto {
    private String accessToken;
    private String refreshToken;
}

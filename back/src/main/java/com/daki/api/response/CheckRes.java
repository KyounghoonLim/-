package com.daki.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 서버 요청에대한 기본 응답값(바디) 정의.
 */
@Getter
@Setter
@ApiModel("CheckResponse")
public class CheckRes {
    @ApiModelProperty(name="응답 메세지", example = "OK")
    String statusText = null;

    public CheckRes() {}

    public static CheckRes of(String message) {
        CheckRes body = new CheckRes();
        body.statusText = message;
        return body;
    }
}

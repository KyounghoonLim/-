package com.daki.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
@ApiModel("UserPasswordRequest")
public class UserPasswordReq {

    @ApiModelProperty(name="비밀번호")
    String memberPassword;

}

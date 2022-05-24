package com.daki.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
@ApiModel("UserModifyRequest")
public class UserModifyReq {

    @ApiModelProperty(name="닉네임")
    String nickName;

    @ApiModelProperty(name="비밀번호")
    String password;

}

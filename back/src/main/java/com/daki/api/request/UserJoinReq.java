package com.daki.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@ApiModel("JoinRequest")
@AllArgsConstructor
public class UserJoinReq {
    @ApiModelProperty(name="이메일(=ID)")
    String email;

    @ApiModelProperty(name="닉네임")
    String nickName;

    @ApiModelProperty(name="비밀번호")
    String password;

    @ApiModelProperty(name="생년월일")
    String birth;

    @ApiModelProperty(name="성별")
    String gender;

    @ApiModelProperty(name="캐릭터 종류")
    int dollType;

}

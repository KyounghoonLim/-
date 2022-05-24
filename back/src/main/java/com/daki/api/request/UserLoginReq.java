package com.daki.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Getter
@Setter
@ApiModel("LoginRequest")
@AllArgsConstructor
public class UserLoginReq {
    @ApiModelProperty(name="이메일(=ID)")
    String email;

    @ApiModelProperty(name="비밀번호")
    String password;


    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }

}

package com.daki.api.service;

import com.daki.api.request.*;
import com.daki.common.config.TokenDto;
import com.daki.db.entity.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
    User createUser(UserJoinReq userJoinReq);
    User getUserByUserId(String email);
    ResponseEntity loginUser(UserLoginReq userLoginReq);
    Boolean checkEmail(String email);
    Boolean checkNickName(String nickName);
    Boolean checkPassword(UserPasswordReq passwordReq);
    TokenDto reissue(TokenRequestDto tokenRequestDto);
    User getMyInfo();
    void modify(UserModifyReq modifyReq);
    void remove();
    ResponseEntity tokenEnter(HttpServletRequest request, Object body, int status); //requsetHeader의 refresh토큰으로 token재발급 해줌 + body와 새 토큰을 줄 것임
}
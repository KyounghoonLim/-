package com.daki.api.controller;

import com.daki.api.request.TokenRequestDto;
import com.daki.api.request.UserJoinReq;
import com.daki.api.request.UserLoginReq;
import com.daki.api.response.BaseRes;
import com.daki.api.response.CheckRes;
import com.daki.api.response.UserJoinRes;
import com.daki.api.response.UserLoginRes;
import com.daki.api.service.UserService;
import com.daki.common.config.TokenDto;
import com.daki.common.util.JwtTokenUtil;
import com.daki.db.entity.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    @ApiOperation(value = "회원 가입", notes = "회원가입")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<UserJoinRes> join(
            @RequestBody @ApiParam(value="회원가입 정보", required = true) UserJoinReq userJoinReq) {
//        System.out.println("================================Enter Controller===============================");
//        System.out.println(userJoinReq.toString());
        User user = userService.createUser(userJoinReq);

        return ResponseEntity.status(200).body(UserJoinRes.of(user, userJoinReq.getDollType()));
    }

    @PostMapping("/login")
    @ApiOperation(value = "로그인", notes = "로그인")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = UserLoginRes.class),
            @ApiResponse(code = 401, message = "인증 실패", response = BaseRes.class),
            @ApiResponse(code = 404, message = "사용자 없음", response = BaseRes.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseRes.class)
    })
    public ResponseEntity<TokenDto> login(@RequestBody @ApiParam(value="로그인 정보", required = true) UserLoginReq userLoginReq) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Test", "test!!!!!!");
        Object object = new Object();
        return userService.loginUser(userLoginReq);
//        return ResponseEntity.ok(userService.loginUser(userLoginReq));
    }

    @PostMapping("/reToken")
    @ApiOperation(value = "토큰 재발급", notes = "기본 토큰을 받아서 토큰을 재발급 해줌")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = UserLoginRes.class),
            @ApiResponse(code = 401, message = "인증 실패", response = BaseRes.class),
            @ApiResponse(code = 404, message = "사용자 없음", response = BaseRes.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseRes.class)
    })
    public ResponseEntity<TokenDto> reToken(@RequestBody @ApiParam(value="토큰정보", required = true) TokenRequestDto tokenRequestDto) {
        return ResponseEntity.ok(userService.reissue(tokenRequestDto));
    }

    @GetMapping("/emailCheck/{userEmail}")
    @ApiOperation(value = "이메일(=아이디)", notes = "이메일(=아이디) 중복체크요청")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = UserLoginRes.class),
            @ApiResponse(code = 401, message = "중복 존재", response = BaseRes.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseRes.class)
    })
    public ResponseEntity<CheckRes> emailCheck(@PathVariable("userEmail") String userEmail) {
        boolean findCheck = userService.checkEmail(userEmail);
        if(findCheck) return ResponseEntity.status(500).body(CheckRes.of("Fail"));
        else return ResponseEntity.status(200).body(CheckRes.of("OK"));
    }

    @GetMapping("/nickNameCheck/{nickName}")
    @ApiOperation(value = "닉네임", notes = "닉네임 중복체크요청")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = UserLoginRes.class),
            @ApiResponse(code = 401, message = "중복 존재", response = BaseRes.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseRes.class)
    })
    public ResponseEntity<CheckRes> nickNameCheck(@PathVariable("nickName") String nickName) {
        boolean findCheck = userService.checkNickName(nickName);
        if(findCheck) return ResponseEntity.status(500).body(CheckRes.of("Fail"));
        else return ResponseEntity.status(200).body(CheckRes.of("OK"));
    }

}

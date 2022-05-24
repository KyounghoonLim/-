package com.daki.api.controller;

import com.daki.api.request.*;
import com.daki.api.response.*;
import com.daki.api.service.UserService;
import com.daki.common.config.TokenDto;
import com.daki.db.entity.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class UserInfoController {

    @Autowired
    UserService userService;

    @GetMapping
    @ApiOperation(value = "내 정보 보기 ", notes = "내 정보 보기")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<MyInfoRes> findMyInfo(HttpServletRequest httpServletRequest) {
        return userService.tokenEnter(httpServletRequest, MyInfoRes.of(userService.getMyInfo()), 200);
    }

    @GetMapping("/test")
    public boolean Test(){
        return true;
    }

    @PatchMapping("/modify")
    @ApiOperation(value = "내 정보 수정", notes = "내 정보 수정")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = UserLoginRes.class),
            @ApiResponse(code = 401, message = "인증 실패", response = BaseRes.class),
            @ApiResponse(code = 404, message = "사용자 없음", response = BaseRes.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseRes.class)
    })
    public ResponseEntity<BaseRes> modify(@RequestBody @ApiParam(value="수정 비밀전호", required = true) UserModifyReq modifyReq) {

        try {
            userService.modify(modifyReq);
        }catch (Exception e){
            return ResponseEntity.status(500).body(BaseRes.of(500, "Fail"));
        }
        return ResponseEntity.status(200).body(BaseRes.of(200, "Success"));
    }

    @DeleteMapping
    @ApiOperation(value = "회원 탈퇴", notes = "회원 탈퇴")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = UserLoginRes.class),
            @ApiResponse(code = 401, message = "인증 실패", response = BaseRes.class),
            @ApiResponse(code = 404, message = "사용자 없음", response = BaseRes.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseRes.class)
    })
    public ResponseEntity<BaseRes> remove() {
        try {
            userService.remove();
        }catch (Exception e){
            return ResponseEntity.status(500).body(BaseRes.of(500, "Fail"));
        }
        return ResponseEntity.status(200).body(BaseRes.of(200, "Success"));
    }

    @PostMapping("/checkPassword")
    @ApiOperation(value = "비밀번호 확인", notes = "비밀번호를 통한 본인 확인 요청")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = UserLoginRes.class),
            @ApiResponse(code = 401, message = "인증 실패", response = BaseRes.class),
            @ApiResponse(code = 404, message = "사용자 없음", response = BaseRes.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseRes.class)
    })
    public ResponseEntity<Boolean> checkPassword(
            @RequestBody @ApiParam(value="비밀번호 정보", required = true) UserPasswordReq passwordReq) {
        return ResponseEntity.ok(userService.checkPassword(passwordReq));
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

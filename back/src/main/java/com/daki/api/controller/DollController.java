package com.daki.api.controller;

import com.daki.api.request.DollCreateReq;
import com.daki.api.request.DollLikeableUpdateReq;
import com.daki.api.request.DollUpdateReq;
import com.daki.api.response.*;
import com.daki.api.service.DollService;
import com.daki.api.service.DollServiceImpl;
import com.daki.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/doll")
public class DollController {

    @Autowired
    DollServiceImpl dollService;

    @Autowired
    UserService userService;

    @GetMapping("/{dollNo}")
    public ResponseEntity<DollReadRes> getDollInfo(@PathVariable Long dollNo,
                                                   HttpServletRequest httpServletRequest){
        DollReadRes dollReadRes = dollService.readDollInfo(dollNo);
        return userService.tokenEnter(httpServletRequest,dollReadRes, 200);
    }

    @PutMapping
    public ResponseEntity<DollUpdateRes> updateDollInfo(@RequestBody DollUpdateReq dollUpdateReq,
                                                        HttpServletRequest httpServletRequest){
        DollUpdateRes dollUpdateRes = dollService.updateDollInfo(dollUpdateReq);
        return userService.tokenEnter(httpServletRequest, dollUpdateRes, 200);

    }

    @DeleteMapping("/{dollNo}")
    public ResponseEntity<DollDeleteRes> deleteDoll(@PathVariable Long dollNo,
                                                    HttpServletRequest httpServletRequest){
        DollDeleteRes dollDeleteRes = dollService.deleteDoll(dollNo);
        return userService.tokenEnter(httpServletRequest, dollDeleteRes, 200);

    }

    @PutMapping("/likeable")
    public ResponseEntity<DollLikeableUpdateRes> updateDollLikeable(@RequestBody DollLikeableUpdateReq dollLikeableUpdateReq,
                                                                    HttpServletRequest httpServletRequest){
        DollLikeableUpdateRes dollLikeableUpdateRes = dollService.updateDollLikeable(dollLikeableUpdateReq);

        return userService.tokenEnter(httpServletRequest, dollLikeableUpdateRes, 200);

    }



}

package com.daki.api.controller;

import com.daki.api.request.UserItemCreateReq;
import com.daki.api.request.UserItemUpdateWearStateReq;
import com.daki.api.response.UserItemCreateRes;
import com.daki.api.response.UserItemReadResInterface;
import com.daki.api.response.UserItemUpdateWearStateRes;
import com.daki.api.service.UserItemServiceImpl;
import com.daki.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/useritem")
public class UserItemController {

    @Autowired
    UserItemServiceImpl useritemService;

    @Autowired
    UserService userService;

    @GetMapping("{dollNo}")
    ResponseEntity<List<UserItemReadResInterface>> readUserItem(@PathVariable Long dollNo,
                                                                HttpServletRequest httpServletRequest){
        List<UserItemReadResInterface> userItemReadRes = useritemService.readUserItem(dollNo);

        return userService.tokenEnter(httpServletRequest, userItemReadRes, 200);
    }

    @PostMapping
    ResponseEntity<UserItemCreateRes> createUserItem(@RequestBody UserItemCreateReq userItemCreateReq,
                                                     HttpServletRequest httpServletRequest){
        UserItemCreateRes userItemCreateRes = useritemService.createUserItem(userItemCreateReq);

        return userService.tokenEnter(httpServletRequest, userItemCreateRes, 200);
    }

    @PutMapping("/wear")
    ResponseEntity<UserItemUpdateWearStateRes> updateWearState(@RequestBody UserItemUpdateWearStateReq userItemUpdateWearStateReq,
                                                               HttpServletRequest httpServletRequest){
        UserItemUpdateWearStateRes userItemUpdateWearStateRes = useritemService.updateWearState(userItemUpdateWearStateReq);



        return userService.tokenEnter(httpServletRequest, userItemUpdateWearStateRes, 200);
    }

}
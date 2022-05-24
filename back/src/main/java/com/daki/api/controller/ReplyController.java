package com.daki.api.controller;

import com.daki.api.request.ReplyRegistReqDto;
import com.daki.api.service.ReplyService;
import com.daki.db.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ReplyController {

    private ReplyService replyService;

    @Autowired
    public ReplyController(ReplyService replyService) {
        this.replyService = replyService;
    }

    @PostMapping("/board/{boardNo}/reply")
    public Long regist(@PathVariable Long boardNo, @RequestBody ReplyRegistReqDto reqDto, Authentication authentication) {
        User user = (User) authentication.getDetails();
        return replyService.regist(reqDto, boardNo, user);
    }
}

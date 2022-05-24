package com.daki.api.controller;

import com.daki.api.request.BoardModifyReqDto;
import com.daki.api.request.BoardRegistReqDto;
import com.daki.api.response.BoardListResDto;
import com.daki.api.response.BoardModifyResDto;
import com.daki.api.response.BoardRegistResDto;
import com.daki.api.response.BoardResDto;
import com.daki.api.service.BoardService;
import com.daki.api.service.UserService;
import com.daki.common.util.SecurityUtil;
import com.daki.db.entity.Board;
import com.daki.db.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/board")
public class BoardController {

    private BoardService boardService;
    private UserService userService;

    public BoardController(BoardService boardService, UserService userService) {
        this.boardService = boardService;
        this.userService = userService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<BoardListResDto>> getList(HttpServletRequest httpServletRequest) {
        return userService.tokenEnter(httpServletRequest, boardService.getList(), 200);
    }

    @GetMapping("/{boardNo}")
    public ResponseEntity<BoardResDto> getBoard(@PathVariable Long boardNo, HttpServletRequest httpServletRequest) {
        return  userService.tokenEnter(httpServletRequest, boardService.findById(boardNo), 200);
    }

    @PostMapping
    public ResponseEntity<Long> regist(@RequestBody BoardRegistReqDto reqDto, HttpServletRequest httpServletRequest) {
        User user = userService.getMyInfo();
        return userService.tokenEnter(httpServletRequest, boardService.register(reqDto,user), 200);
    }

    @PatchMapping("/{boardNo}")
    public ResponseEntity<Long> modify (@PathVariable Long boardNo, @RequestBody BoardModifyReqDto reqDto,
                                        HttpServletRequest httpServletRequest) {
        User user = userService.getMyInfo();
        BoardResDto res = boardService.findById(boardNo);
        if(user.getUserNickname() != res.getNickName()) {
            throw new RuntimeException("권한이 없습니다");
        }
        boardService.modify(boardNo, reqDto);
        return userService.tokenEnter(httpServletRequest, res.getBoardNo(), 200);
    }

    @DeleteMapping("/{boardNo}")
    public ResponseEntity<Long> delete (@PathVariable Long boardNo,
                                        HttpServletRequest httpServletRequest) {
        User user = userService.getMyInfo();

        if(user.getUserNickname() != boardService.findById(boardNo).getNickName()) {
            throw new RuntimeException("권한이 없습니다");
        }

        return userService.tokenEnter(httpServletRequest, boardService.delete(boardNo), 200);
    }
}


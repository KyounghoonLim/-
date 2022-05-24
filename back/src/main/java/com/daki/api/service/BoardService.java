package com.daki.api.service;

import com.daki.api.request.BoardModifyReqDto;
import com.daki.api.request.BoardRegistReqDto;
import com.daki.api.response.BoardListResDto;
import com.daki.api.response.BoardModifyResDto;
import com.daki.api.response.BoardRegistResDto;
import com.daki.api.response.BoardResDto;
import com.daki.db.entity.Board;
import com.daki.db.entity.User;
import com.daki.db.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public interface BoardService {

    public Long register(BoardRegistReqDto dto, User user);
    public BoardResDto findById(Long boardNo);
    public List<BoardListResDto> getList();
    public Long modify(Long boardNo, BoardModifyReqDto reqDto);
    public Long delete(Long boardNo);


}

package com.daki.api.service;

import com.daki.api.request.BoardModifyReqDto;
import com.daki.api.request.BoardRegistReqDto;
import com.daki.api.response.BoardListResDto;
import com.daki.api.response.BoardRegistResDto;
import com.daki.api.response.BoardResDto;
import com.daki.db.entity.Board;
import com.daki.db.entity.User;
import com.daki.db.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BoardServiceImpl implements BoardService{

    private BoardRepository boardRepository;

    @Autowired
    public BoardServiceImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    public List<BoardListResDto> getList() {
        List<Board> list = boardRepository.findAll();
        List<BoardListResDto> dtoList = new ArrayList<>();

        return list.stream().map(dto -> new BoardListResDto(dto.getBoardNo(), dto.getBoardHeadLine(),
                dto.getBoardTitle(), dto.getBoardDate())).collect(Collectors.toList());

    }

    @Override
    public Long modify(Long boardNo, BoardModifyReqDto reqDto) {
        Board board = boardRepository.findById(boardNo).orElseThrow(() -> new IllegalArgumentException("게시글 번호가 없습니다"));
        board.modify(reqDto);
        return board.getBoardNo();
    }

    @Override
    public Long delete(Long boardNo) {
        Board board = boardRepository.findById(boardNo).orElseThrow(() -> new IllegalArgumentException("게시글 번호가 없습니다"));
        boardRepository.delete(board);
        return board.getBoardNo();

    }

    @Override
    public Long register(BoardRegistReqDto dto, User user) {
        Board board = dto.toEntity(user);
        boardRepository.save(board);
        return board.getBoardNo();
    }

    @Override
    public BoardResDto findById(Long boardNo) {
        Board board = boardRepository.findById(boardNo).orElseThrow(() -> new IllegalArgumentException("해당 게시글 번호가 없습니다."));
        return new BoardResDto().toBoardResDto(board);
    }




}

package com.daki.api.service;

import com.daki.api.request.ReplyRegistReqDto;
import com.daki.db.entity.Board;
import com.daki.db.entity.Reply;
import com.daki.db.entity.User;
import com.daki.db.repository.BoardRepository;
import com.daki.db.repository.ReplyRepository;
import com.daki.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReplyServiceImpl implements ReplyService{

    private ReplyRepository replyRepository;
    private BoardRepository boardRepository;

    @Autowired
    public ReplyServiceImpl(ReplyRepository replyRepository, BoardRepository boardRepository) {
        this.replyRepository = replyRepository;
        this.boardRepository = boardRepository;
    }


    @Override
    public Long regist(ReplyRegistReqDto reqDto, Long boardNo, User user) {
        Board board = boardRepository.findById(boardNo).orElseThrow(() -> new IllegalArgumentException("해당 게시글 번호가 없습니다 "));
        Reply reply = reqDto.toEntity(board, user);
        replyRepository.save(reply);
        return reply.getReplyNo();
    }
}

package com.daki.db.entity;

import com.daki.api.request.BoardModifyReqDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@Table(name = "board")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_no")
    private Long boardNo;

    @Column(name = "board_head_line")
    private String boardHeadLine;

    @Column(name = "board_title")
    private String boardTitle;

    @Column(name = "board_content")
    private String boardContent;

    @Column(name = "board_date")
    private LocalDateTime boardDate;

    @ManyToOne
    @JoinColumn(name = "user_no")
    private User user;

    public Board(){}

    public Board(String boardHeadLine, String boardTitle, String boardContent, LocalDateTime boardDate, User user) {
        this.boardHeadLine = boardHeadLine;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.boardDate = boardDate;
        this.user = user;
    }

    public Board(Long boardNo, String boardHeadLine, String boardTitle, String boardContent, LocalDateTime boardDate, User user) {
        this.boardNo = boardNo;
        this.boardHeadLine = boardHeadLine;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.boardDate = boardDate;
        this.user = user;
    }

    public void modify(BoardModifyReqDto dto) {
        this.boardHeadLine = dto.getHeadLine();
        this.boardContent = dto.getContent();
        this.boardDate = LocalDateTime.now();
    }
}
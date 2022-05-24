package com.daki.db.entity;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@Table(name = "reply")
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_no")
    private Long replyNo;

    @Column(name = "reply_content")
    private String replyContent;

    @Column(name = "reply_date")
    private LocalDateTime replyDate;

    @ManyToOne
    @JoinColumn(name = "user_no")
    private User user;

    @ManyToOne
    @JoinColumn(name = "board_no")
    private Board board;

    public Reply(String replyContent, LocalDateTime replyDate, User user, Board board) {
        this.replyContent = replyContent;
        this.replyDate = replyDate;
        this.user = user;
        this.board = board;
    }

    public Reply(Long replyNo, String replyContent, LocalDateTime replyDate, User user, Board board) {
        this.replyNo = replyNo;
        this.replyContent = replyContent;
        this.replyDate = replyDate;
        this.user = user;
        this.board = board;
    }
}
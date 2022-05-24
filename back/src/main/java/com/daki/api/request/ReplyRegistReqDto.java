package com.daki.api.request;


import com.daki.db.entity.Board;
import com.daki.db.entity.Reply;
import com.daki.db.entity.User;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@ApiModel
public class ReplyRegistReqDto {

    private String comment;



    public Reply toEntity(Board board, User user) {
        return Reply.builder()
                .replyContent(this.comment)
                .replyDate(LocalDateTime.now())
                .user(user)
                .board(board)
                .build();
    }

    /*
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
     */

}

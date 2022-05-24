package com.daki.api.response;

import com.daki.db.entity.Board;
import com.daki.db.entity.User;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@ApiModel

public class BoardResDto {
    private Long boardNo;
    private String headLine;
    private String title;
    private String content;
    private LocalDateTime registDate;
    private String nickName;

    public BoardResDto(Long boardNo, String headLine, String title, String content, LocalDateTime registDate, String nickName) {
        this.boardNo = boardNo;
        this.headLine = headLine;
        this.title = title;
        this.content = content;
        this.registDate = registDate;
        this.nickName = nickName;
    }

    public BoardResDto() {

    }

    public BoardResDto toBoardResDto(Board board) {
        return BoardResDto.builder()
                .boardNo(board.getBoardNo())
                .headLine(board.getBoardHeadLine())
                .title(board.getBoardTitle())
                .content(board.getBoardTitle())
                .registDate(board.getBoardDate())
                .nickName(board.getUser().getUserNickname())
                .build();
    }
}

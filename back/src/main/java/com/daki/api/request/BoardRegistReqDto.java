package com.daki.api.request;

import com.daki.api.response.BoardRegistResDto;
import com.daki.db.entity.Board;
import com.daki.db.entity.User;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@ApiModel
public class BoardRegistReqDto {

    private String headLine;
    private String title;
    private String content;



    public BoardRegistReqDto(String headLine, String title, String content) {
        this.headLine = headLine;
        this.title = title;
        this.content = content;
    }

    public Board toEntity(User user) {
        return Board.builder()
                .boardHeadLine(headLine)
                .boardTitle(title)
                .boardContent(content)
                .boardDate(LocalDateTime.now())
                .user(user)
                .build();
    }


}

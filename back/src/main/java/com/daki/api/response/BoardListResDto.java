package com.daki.api.response;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@ApiModel("BoardListResDto")
public class BoardListResDto {

    private Long boardNo;
    private String headLine;
    private String title;
    private LocalDateTime registDate;

    public BoardListResDto(Long boardNo, String headLine, String title, LocalDateTime registDate) {
        this.boardNo = boardNo;
        this.headLine = headLine;
        this.title = title;
        this.registDate = registDate;
    }

    public BoardListResDto() {

    }
}

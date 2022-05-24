package com.daki.api.response;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@ApiModel

public class BoardRegistResDto {

    private Long no;
    private String headLine;
    private String title;
    private String content;
    private LocalDateTime date;

}

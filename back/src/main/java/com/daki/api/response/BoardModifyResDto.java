package com.daki.api.response;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@ApiModel

public class BoardModifyResDto {

    private Long no;
    private String headLine;
    private String title;
    private String content;
    private LocalDateTime localDateTime;
}

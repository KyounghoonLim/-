package com.daki.api.request;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@ApiModel
public class BoardModifyReqDto {

    private String headLine;
    private String title;
    private String content;


}

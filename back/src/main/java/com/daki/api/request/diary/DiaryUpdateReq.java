package com.daki.api.request.diary;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class DiaryUpdateReq {

    private Long diaryNo;

    private String title;

    private String content;
}

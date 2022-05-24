package com.daki.api.response.diary;

import com.daki.db.entity.Diary;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ApiModel

public class DiaryReadRes {

    private Long diaryNo;

    private String title;

    private String content;

    private LocalDateTime diaryDate;

    public DiaryReadRes(Diary diary) {
        this.diaryNo = diary.getDiaryNo();
        this.title = diary.getDiaryTitle();
        this.content = diary.getDiaryContent();
        this.diaryDate = diary.getDiaryDate();
    }
}

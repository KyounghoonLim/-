package com.daki.api.service;

import com.daki.api.request.diary.*;
import com.daki.api.response.diary.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface DiaryService {

    DiaryCreateRes createDiary(DiaryCreateReq diaryCreateReq);
    DiaryReadRes readDiary(Long diaryNo);
    DiaryReadAllRes readAllDiary(DiaryReadAllReq diaryReadAllReq);
    DiaryUpdateRes updateDiary(DiaryUpdateReq diaryUpdateReq);
    DiaryDeleteRes deleteDiary(Long diaryNo);

    Map<LocalDate, List<DiaryTitleDateRes>> findTitleByDate(int year, int month);
    List<DiaryTitlePageRes> findTitleByPageNo(int pageNo);

}

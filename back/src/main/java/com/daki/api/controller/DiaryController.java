package com.daki.api.controller;


import com.daki.api.request.diary.DiaryCreateReq;
import com.daki.api.request.diary.DiaryUpdateReq;
import com.daki.api.response.diary.*;
import com.daki.api.service.DiaryServiceImpl;
import com.daki.api.service.UserService;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/diary")
public class DiaryController {

    @Autowired
    DiaryServiceImpl diaryService;

    @Autowired
    UserService userService;

    @GetMapping("/findPage/{pageNo}")
    public ResponseEntity<DiaryTitlePageRes> findPage(@PathVariable("pageNo") int pageNo,
                                                   HttpServletRequest httpServletRequest){
        List<DiaryTitlePageRes> resList = diaryService.findTitleByPageNo(pageNo);
        return userService.tokenEnter(httpServletRequest, resList, 200);
    }

    @GetMapping("/findDate/{year}/{month}")
    public ResponseEntity<Map<LocalDate, List<DiaryTitleDateRes>>> findDate(@PathVariable("year") int year,
                                                                            @PathVariable("month") int month,
                                                                            HttpServletRequest httpServletRequest){
        Map<LocalDate, List<DiaryTitleDateRes>> resList = diaryService.findTitleByDate(year, month);
        return userService.tokenEnter(httpServletRequest, resList, 200);
    }

    @PostMapping
    public ResponseEntity<DiaryCreateRes> createDiary(@RequestBody DiaryCreateReq diaryCreateReq
                                                        , HttpServletRequest httpServletRequest){
        DiaryCreateRes diaryCreateRes = diaryService.createDiary(diaryCreateReq);
        return userService.tokenEnter(httpServletRequest, diaryCreateRes, 200);
    }

    @GetMapping("/{diaryNo}")
    public ResponseEntity<DiaryReadRes> readDiary(@PathVariable Long diaryNo,
                                                  HttpServletRequest httpServletRequest) throws ParseException {
        DiaryReadRes diaryReadRes = diaryService.readDiary(diaryNo);

        return userService.tokenEnter(httpServletRequest, diaryReadRes, 200);
    }

    @PutMapping
    public ResponseEntity<DiaryUpdateRes> updateDiary(@RequestBody DiaryUpdateReq diaryUpdateReq,
                                                      HttpServletRequest httpServletRequest){
        DiaryUpdateRes diaryUpdateRes = diaryService.updateDiary(diaryUpdateReq);

        return userService.tokenEnter(httpServletRequest, diaryUpdateRes, 200);
    }

    @DeleteMapping("/{diaryNo}")
    public ResponseEntity<DiaryDeleteRes> deleteDiary(@PathVariable Long diaryNo,
                                                      HttpServletRequest httpServletRequest){
        DiaryDeleteRes diaryDeleteRes = diaryService.deleteDiary(diaryNo);
        System.out.println("====================Success diary delete=======================");
        return userService.tokenEnter(httpServletRequest, diaryDeleteRes, 200);
    }




}

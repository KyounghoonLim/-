//package com.daki.api.service;
//
//import com.daki.api.request.diary.DiaryCreateReq;
//import com.daki.api.request.diary.DiaryReadReq;
//import com.daki.api.request.diary.DiaryUpdateReq;
//import com.daki.api.response.diary.DiaryCreateRes;
//import com.daki.api.response.diary.DiaryReadRes;
//import com.daki.api.response.diary.DiaryUpdateRes;
//import com.daki.db.entity.Diary;
//import com.daki.db.entity.User;
//import com.daki.db.repository.DiaryRepository;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//
//import java.time.LocalDateTime;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@Transactional
//class DiaryServiceImplTest {
//
//    @PersistenceContext
//    EntityManager em;
//
//    @Autowired
//    DiaryServiceImpl diaryService;
//
//    @Autowired
//    DiaryRepository diaryRepository;
//
//    User userA = new User("aa123@gmail.com", "kim" ,"aa123", "aa123", "22/04/27", false, 1000);
//    Diary diaryA = new Diary(10L,"title", "content", LocalDateTime.now(), userA);
//
//    @Test
//    void createDiary() {
//        //given
//        DiaryCreateReq diaryCreateReq = new DiaryCreateReq(diaryA.getDiaryTitle(), diaryA.getDiaryContent(), diaryA.getDiaryDate(), diaryA.getUser());
//
//        //when
//        DiaryCreateRes diaryCreateRes = diaryService.createDiary(diaryCreateReq);
//
//        //then
//        //id는 비교하면 에러남. auto increment 때문
//        //Assertions.assertThat(diaryCreateRes.getDiaryNo()).isEqualTo(diaryA.getDiaryNo());
//
//        Assertions.assertThat(diaryCreateRes.getDiaryDate()).isEqualTo(diaryA.getDiaryDate());
//    }
//
//    @Test
//    void readDiary() {
//        //given
//        Diary savedDiaryA = diaryRepository.save(diaryA);
//        DiaryReadReq diaryReadReq = new DiaryReadReq(savedDiaryA.getDiaryNo());
//
//        //when
//        DiaryReadRes diaryReadRes = diaryService.readDiary(diaryReadReq);
//
//        //then
//        Assertions.assertThat(diaryReadRes.getDiaryTitle()).isEqualTo(savedDiaryA.getDiaryTitle());
//    }
//
//    @Test
//    void readAllDiary() {
//    }
//
//    @Test
//    void updateDiary() {
//        //자꾸 id가 문제된다. 업데이트 하려면 id로 가져온다음에 해야되는데 테스트 케이스니 일단 생성을 해야하는데
//        //생성하니 id가 auto increment 때문에 안 맞는다.. 자꾸..
//
//        //given
//        Mockito.when(diaryRepository.save(Mockito.any(Diary.class))).thenReturn(diaryA);
//
//        DiaryUpdateReq diaryUpdateReq = new DiaryUpdateReq( diaryA.getDiaryNo(), "update_title", "update_content", LocalDateTime.now());
////        em.persist(diaryA);
//        //이거하면 detached 라고 뜨네
//
//        //when
//        DiaryUpdateRes diaryUpdateRes = diaryService.updateDiary(diaryUpdateReq);
//
//        //then
//        Assertions.assertThat(diaryUpdateRes.getDiaryContent()).isEqualTo(diaryA.getDiaryContent());
//    }
//
//    @Test
//    void deleteDiary() {
//    }
//}
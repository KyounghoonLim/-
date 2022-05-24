//package com.daki.api.controller;
//
//import com.daki.api.request.DollLikeableUpdateReq;
//import com.daki.api.request.DollUpdateReq;
//import com.daki.api.response.DollDeleteRes;
//import com.daki.api.response.DollLikeableUpdateRes;
//import com.daki.api.response.DollReadRes;
//import com.daki.api.response.DollUpdateRes;
//import com.daki.api.service.DollServiceImpl;
//import com.daki.db.entity.Doll;
//import com.daki.db.entity.User;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.BDDMockito;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//
//@WebMvcTest(DollController.class)
//class DollControllerTest {
//
//    @Autowired
//    private DollController dollController;
//
//    @MockBean
//    private DollServiceImpl dollService;
//
//    private MockMvc mockMvc;
//
//    ObjectMapper om = new ObjectMapper();
//
//    private final User userA = new User(3L,"aa123@gmail.com", "kim" , "aa123", "aa123", "22/04/27", false, 1000);
//    private final Doll dollA = new Doll(100L,77, userA, Skin.black);
//    private final DollReadRes dollReadResA = new DollReadRes(dollA.getDollNo(), dollA.getDollLikeable(), dollA.getSkin());
//
//    private final DollUpdateReq dollUpdateReqA = new DollUpdateReq(userA.getUserNo(), dollA.getDollLikeable(), Skin.black);
//    private final DollUpdateRes dollUpdateResA = new DollUpdateRes(dollA.getDollNo(), dollUpdateReqA.getDollLikeable(), dollUpdateReqA.getSkin());
//
//    private final DollDeleteRes dollDeleteResA = new DollDeleteRes("delete success");
//
//    private final DollLikeableUpdateReq dollLikeableUpdateReq = new DollLikeableUpdateReq(dollA.getDollNo(), 10);
//    private final DollLikeableUpdateRes dollLikeableUpdateRes = new DollLikeableUpdateRes(dollA.getDollNo(), dollA.getDollLikeable() + dollLikeableUpdateReq.getChangeAmount());
//
//    //테스트마다 매번 MockMvc 초기화
//    @BeforeEach
//    void setUp() {
//        mockMvc = MockMvcBuilders.standaloneSetup(dollController).build();
//    }
//
//    //perform : 가상의 request 처리
//    //expect : 결과, 가상의 response 검증.
//    //do : 테스트 중에 직접 처리할 일 작성. 예를 들면 출력하기.
//    @Test
//    void getDollInfo() throws Exception {
//        Mockito.when(dollService.readDollInfo(dollA.getDollNo())).thenReturn(dollReadResA);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/doll").param("dollNo", "100"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//    }
//
//    @Test
//    void updateDollInfo() throws Exception {
//        BDDMockito.given(dollService.updateDollInfo(Mockito.any(DollUpdateReq.class))).willReturn(dollUpdateResA);
//        //BDDMockito.given(dollService.updateDollInfo(dollUpdateReqA)).willReturn(dollUpdateResA);
//
//        mockMvc.perform(MockMvcRequestBuilders.put("/api/doll").content(om.writeValueAsString(dollUpdateReqA)).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//    }
//
//    @Test
//    void deleteDoll() throws Exception {
//        Mockito.when(dollService.deleteDoll(dollA.getDollNo())).thenReturn(dollDeleteResA);
//
//        mockMvc.perform(MockMvcRequestBuilders.delete("/api/doll").param("dollNo", "100"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//
//
//    }
//
//    @Test
//    void updateDollLikeable() throws Exception{
//        Mockito.when(dollService.updateDollLikeable(Mockito.any(DollLikeableUpdateReq.class))).thenReturn(dollLikeableUpdateRes);
//
//        mockMvc.perform(MockMvcRequestBuilders.put("/api/doll/likeable").content(om.writeValueAsString(dollLikeableUpdateReq)).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//    }
//}
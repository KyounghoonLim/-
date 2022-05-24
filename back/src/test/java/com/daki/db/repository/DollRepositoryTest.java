//package com.daki.db.repository;
//
//import com.daki.db.entity.Doll;
//import com.daki.db.entity.User;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//
//@SpringBootTest
//@Transactional
//class DollRepositoryTest {
//
//    @Autowired
//    UserRepository userRepository;
//
//    @Autowired
//    DollRepository dollRepository;
//
//
//    @Test
//    public void testDoll(){
//        User userA = new User("aa123@gmail.com", "kim" ,"aa123", "aa123", "22/04/27", false, 1000);
//        User userB = new User("aa123@gmail.com", "kim", "aa123", "aa123", "22/04/27", false, 1000);
//        Doll dollA = new Doll(80, userA, Skin.black);
//        Doll dollB = new Doll(90, userB, Skin.black);
//
//        User saveUser = userRepository.save(userA);
//        User saveUserB = userRepository.save(userB);
//        Doll saveDoll = dollRepository.save(dollA);
//        Doll saveDollB = dollRepository.save(dollB);
//
//        Doll findDoll = dollRepository.findByUser(dollA.getUser());
//
//        Assertions.assertThat(findDoll.getDollNo()).isEqualTo(dollA.getDollNo());
//
//        Assertions.assertThat(findDoll).isEqualTo(dollB);
//
//    }
//}
//package com.daki.entity;
//
//import com.daki.db.entity.Doll;
//import com.daki.db.entity.User;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import java.util.List;
//
////innodb로 해야함.
//
//@SpringBootTest
//public class DollTest {
//
//    @PersistenceContext
//    EntityManager em;
//
//    @Test
//    @Transactional // transactional 붙이면 자동으로 롤백됨.
////    @Rollback(false) //롤백 안할거면
//    public void testEntity(){
//        User userA = new User("aa123@gmail.com", "kim" , "aa123", "aa123", "22/04/27", false, 1000);
//        em.persist(userA);
//
////        Assertions.assertThat(userA.getUserNo()).isEqualTo(13L);
//
//
//        Doll dollA = new Doll(100, userA, Skin.black);
//        em.persist(dollA);
//
////        //영속성 컨텐스트의 변경 내용 DB에 반영함. 쿼리가 DB에 반영됨.
////        em.flush();
////
////        //영속성 컨텍스트를 지움.
////        em.clear();
//
//
//        List<Doll> dollList = em.createQuery("select d from Doll d", Doll.class).getResultList();
//
//        for(Doll doll : dollList){
//            System.out.println(doll.toString());
//        }
//    }
//
//}

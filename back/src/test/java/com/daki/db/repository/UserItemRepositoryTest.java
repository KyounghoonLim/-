//package com.daki.db.repository;
//
//import com.daki.api.response.UserItemReadResInterface;
//import com.daki.db.entity.*;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@Transactional  //db에 반영 안됨. rollback 된다.
//class UserItemRepositoryTest {
//
//    @PersistenceContext
//    EntityManager em;
//
//    @Autowired
//    UserItemRepository userItemRepository;
//
//    @Test
//    void findByDoll() {
//        //given
//        User userA = new User("aa123@gmail.com", "kim" ,"aa123", "aa123", "22/04/27", false, 1000);
//        Doll dollA = new Doll(80, userA, Skin.black);
//        Item itemA = new Item(ItemType.top, "testTop1", "Top1URL");
//        UserItem userItemA = new UserItem(false, dollA, itemA);
//
//        em.persist(userA);
//        em.persist(dollA);
//        em.persist(itemA);
//        em.persist(userItemA);
//
//        //when
//
//        List<UserItemReadResInterface> list =  userItemRepository.findByDoll(dollA);
//
//        for (UserItemReadResInterface userItemReadResInterface : list) {
//            System.out.println(userItemReadResInterface.getItemItemName());
//        }
//
//        //then
//        Assertions.assertThat(list.size()).isEqualTo(1);
//
//    }
//
//    @Test
//    void findByDollAndItem() {
//        //given
//        User userA = new User("aa123@gmail.com", "kim" ,"aa123", "aa123", "22/04/27", false, 1000);
//        Doll dollA = new Doll(80, userA, Skin.black);
//        Item itemA = new Item(ItemType.top, "testTop1", "Top1URL");
//        UserItem userItemA = new UserItem(false, dollA, itemA);
//
//        em.persist(userA);
//        em.persist(dollA);
//        em.persist(itemA);
//        em.persist(userItemA);
//
//        //when
//        UserItem findUserItem = userItemRepository.findByDollAndItem(dollA, itemA);
//
//        //then
//        Assertions.assertThat(findUserItem).isEqualTo(userItemA);
//
//    }
//}
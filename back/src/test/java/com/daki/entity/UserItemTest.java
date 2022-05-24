//package com.daki.entity;
//
//import com.daki.api.response.UserItemReadRes;
//import com.daki.db.entity.*;
//import com.daki.db.repository.DollRepository;
//import com.daki.db.repository.ItemRepository;
//import com.daki.db.repository.UserItemRepository;
//import com.daki.db.repository.UserRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import java.util.List;
//
//@SpringBootTest
//@Transactional
//public class UserItemTest {
//
//    @PersistenceContext
//    EntityManager em;
//
//    @Autowired
//    DollRepository dollRepository;
//
//    @Autowired
//    UserRepository userRepository;
//
//    @Autowired
//    ItemRepository itemRepository;
//
//    @Autowired
//    UserItemRepository userItemRepository;
//
//    @Test
//    @Transactional
//    public void testEntity(){
//        User userA = new User("aa123@gmail.com", "kim", "aa123", "aa123", "22/04/27", false, 1000);
////        userRepository.save(userA);
//        em.persist(userA);
//
//        Doll dollA = new Doll((long)200,100, userA, Skin.black);
//        dollRepository.save(dollA);
////        em.persist(dollA);
//
//        Item itemA = new Item(ItemType.top, "testTop1", "Top1URL");
////        itemRepository.save(itemA);
//        em.persist(itemA);
//
//        UserItem userItemA = new UserItem(false, dollA, itemA);
////        userItemRepository.save(userItemA);
//        em.persist(userItemA);
//
////        em.flush();
//
//        List<UserItem> list = em.createQuery("select u from UserItem u").getResultList();
//
////        List<UserItem> list = userItemRepository.findAll();
//
//        for (UserItem userItem : list) {
//            System.out.println(userItem.toString());
//        }
//
//    }
//
//
//
//}

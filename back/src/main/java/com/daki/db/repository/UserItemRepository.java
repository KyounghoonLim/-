package com.daki.db.repository;

import com.daki.api.response.UserItemCategoriesInterface;
import com.daki.api.response.UserItemReadRes;
import com.daki.api.response.UserItemReadResInterface;
import com.daki.db.entity.Doll;
import com.daki.db.entity.Item;
import com.daki.db.entity.UserItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UserItemRepository extends JpaRepository<UserItem, Long> {

//    @Query(value="select A.item_no as itemNo, A.wear_flag AS wearFlag, B.item_image AS itemImage, B.item_name as itemName, B.item_type as itemType from user_item as A\n" +
//            "left join item as B\n" +
//            "on A.item_no = B.item_no\n" +
//            "where doll_no=:doll", nativeQuery = true)
    List<UserItemReadResInterface> findByDoll(@Param("doll") Doll doll);

    UserItem findByDollAndItem(Doll doll, Item item);
    List<UserItem> findByDollAndWearFlag(Doll doll, int wearFlag);

    @Query(value= "select B.item_no as itemNo, isnull(B.item_no) as isNull from `user_item` as A\n" +
            "left join (select item_no from `item` \n" +
            "where item_categories=(select A.item_categories from item as A\n" +
            "left join user_item as B\n" +
            "on A.item_no = B.item_no\n" +
            "where B.doll_no = :dollNo and B.item_no = :itemNo)) as B\n" +
            "on A.item_no = B.item_no\n" +
            "where doll_no = :dollNo", nativeQuery = true)
    List<UserItemCategoriesInterface> setNo(@Param("dollNo") Doll dollNo, @Param("itemNo") Item itemNo);


    @Transactional
    @Modifying
    @Query(value = "update `user_item` set wear_flag=0\n" +
            "where doll_no= :dollNo and item_no= :itemNo", nativeQuery = true)
    int setNo2(@Param("dollNo") Doll dollNo, @Param("itemNo") Item itemNo);

//    @Query(value="select * from user_item where doll_no=:doll_no", nativeQuery = true)
//    List<UserItem> findByDoll2(@Param("doll_no") Long dollNo);
}

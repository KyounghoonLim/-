package com.daki.db.repository;

import com.daki.api.response.ItemReadResInterface;
import com.daki.db.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query(value="select A.item_no as itemNo, A.item_categories as itemCategories, A.item_image as itemImage, A.item_name as itemName, \n" +
            "A.item_price as itemPrice , ifnull(B.wear_flag, -1) as wearFlag from item as A\n" +
            "left join (select * from `user_item` where doll_no=1)as B\n" +
            "on A.item_no = B.item_no;", nativeQuery = true)
    List<ItemReadResInterface> findByDoll(@Param("doll") Long doll_no);


}

package com.daki.api.service;

import com.daki.api.response.ItemListRes;
import com.daki.api.response.ItemReadResInterface;
import com.daki.db.entity.Doll;
import com.daki.db.entity.Item;
import com.daki.db.entity.UserItem;
import com.daki.db.repository.DollRepository;
import com.daki.db.repository.ItemRepository;
import com.daki.db.repository.UserItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService{

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    DollRepository dollRepository;

    @Autowired
    UserItemRepository userItemRepository;

    public List<ItemListRes> readAllItem(Long dollNo){
        Doll doll = dollRepository.findById(dollNo).get();

        List<Item> itemList = itemRepository.findAll();
        List<ItemListRes> itemListResList = new ArrayList<>();


        for (Item item:itemList) {
            ItemListRes itemListRes = new ItemListRes();
            itemListRes.setItemCategories(item.getItemCategories());
            itemListRes.setItemImage(item.getItemImage());
            itemListRes.setItemName(item.getItemName());
            itemListRes.setItemNo(item.getItemNo());
            itemListRes.setItemPrice(item.getItemPrice());
            UserItem userItem = userItemRepository.findByDollAndItem(doll, item);
            itemListRes.setWearFlag(userItem.getWearFlag());

            itemListResList.add(itemListRes);
        }

        return itemListResList;
    }

}
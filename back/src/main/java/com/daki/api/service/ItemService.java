package com.daki.api.service;

import com.daki.api.response.ItemListRes;
import com.daki.api.response.ItemReadResInterface;
import com.daki.db.entity.Item;

import java.util.List;

public interface ItemService {

    public List<ItemListRes> readAllItem(Long dollNo);

}

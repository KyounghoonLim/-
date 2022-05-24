package com.daki.api.response;

import com.daki.db.entity.Doll;
import com.daki.db.entity.Item;
import com.daki.db.entity.ItemType;
import com.daki.db.entity.UserItem;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import java.util.List;
@ApiModel

public class UserItemReadRes {

    private Long userItemNo;

    private int wearFlag;

    private ItemType itemType;

    private String itemName;

    private String itemImage;

    public Long getUserItemNo() {
        return userItemNo;
    }

    public int getWearFlag() {
        return wearFlag;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setUserItemNo(Long userItemNo) {
        this.userItemNo = userItemNo;
    }

    public void setWearFlag(int wearFlag) {
        this.wearFlag = wearFlag;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public UserItemReadRes(){}
    public UserItemReadRes(Long userItemNo, int wearFlag, ItemType itemType, String itemName, String itemImage) {
        this.userItemNo = userItemNo;
        this.wearFlag = wearFlag;
        this.itemType = itemType;
        this.itemName = itemName;
        this.itemImage = itemImage;
    }



}

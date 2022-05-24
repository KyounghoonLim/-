package com.daki.api.response;


import com.daki.db.entity.Doll;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@ApiModel
@Getter
@Setter
public class ItemListRes {

    private int itemCategories;

    private String itemImage;

    private String itemName;

    private long itemNo;

    private int itemPrice;

    private int wearFlag;

    public ItemListRes() {};

    public ItemListRes(int itemCategories, String itemImage, String itemName, int itemNo, int itemPrice, int wearFlag) {
        this.itemCategories = itemCategories;
        this.itemImage = itemImage;
        this.itemName = itemName;
        this.itemNo = itemNo;
        this.itemPrice = itemPrice;
        this.wearFlag = wearFlag;
    }
}

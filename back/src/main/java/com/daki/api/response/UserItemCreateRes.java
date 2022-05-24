package com.daki.api.response;

import com.daki.db.entity.UserItem;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserItemCreateRes {

    Long userItemNo;
    Long dollNo;
    Long itemNo;
    int wearState;

    public UserItemCreateRes(UserItem userItem){
        this.userItemNo = userItem.getUserItemNo();
        this.dollNo = userItem.getDoll().getDollNo();
        this.itemNo = userItem.getItem().getItemNo();
        this.wearState = userItem.getWearFlag();

    }

}

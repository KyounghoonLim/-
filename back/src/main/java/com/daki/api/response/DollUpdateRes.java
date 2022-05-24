package com.daki.api.response;


import com.daki.db.entity.Doll;
import io.swagger.annotations.ApiModel;

@ApiModel

public class DollUpdateRes {

    private Long dollNo;

    private int dollLikeable;

    private int dollType;

    public Long getDollNo() {
        return dollNo;
    }

    public int getDollType() {
        return dollType;
    }

    public void setDollType(int dollType) {
        this.dollType = dollType;
    }

    public int getDollLikeable() {
        return dollLikeable;
    }


    public void setDollNo(Long dollNo) {
        this.dollNo = dollNo;
    }

    public void setDollLikeable(int dollLikeable) {
        this.dollLikeable = dollLikeable;
    }


    public DollUpdateRes(Long dollNo, int dollLikeable, int dollType) {
        this.dollNo = dollNo;
        this.dollLikeable = dollLikeable;
        this.dollType = dollType;
    }

    public DollUpdateRes(Doll doll){
        this.dollNo = doll.getDollNo();
        this.dollLikeable = doll.getDollLikeable();
        this.dollType = doll.getDollType();
    }

}

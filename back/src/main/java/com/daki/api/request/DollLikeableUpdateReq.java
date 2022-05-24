package com.daki.api.request;

import io.swagger.annotations.ApiModel;

@ApiModel
public class DollLikeableUpdateReq {

    Long dollNo;

    int changeAmount;

    public Long getDollNo() {
        return dollNo;
    }

    public void setDollNo(Long dollNo) {
        this.dollNo = dollNo;
    }

    public int getChangeAmount() {
        return changeAmount;
    }

    public void setChangeAmount(int changeAmount) {
        this.changeAmount = changeAmount;
    }

    public DollLikeableUpdateReq() {
    }

    public DollLikeableUpdateReq(Long dollNo, int changeAmount) {
        this.dollNo = dollNo;
        this.changeAmount = changeAmount;
    }
}

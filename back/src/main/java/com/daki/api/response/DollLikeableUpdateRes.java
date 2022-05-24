package com.daki.api.response;

import com.daki.db.entity.Doll;
import io.swagger.annotations.ApiModel;

@ApiModel

public class DollLikeableUpdateRes {

    private Long dollNo;

    private int dollLikeable;

    public Long getDollNo() {
        return dollNo;
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

    public DollLikeableUpdateRes() {}
    public DollLikeableUpdateRes(Long dollNo, int dollLikeable) {
        this.dollNo = dollNo;
        this.dollLikeable = dollLikeable;
    }

    public DollLikeableUpdateRes(Doll doll) {
        this.dollNo = doll.getDollNo();
        this.dollLikeable = doll.getDollLikeable();
    }
}

package com.daki.api.response;

import io.swagger.annotations.ApiModel;

@ApiModel

public class DollReadRes {

    private Long dollNo;
    private int dollLikeable;
    private int dollType;

    public Long getDollNo() { return dollNo;}

    public int getDollLikeable() {
        return dollLikeable;
    }

    public void setDollNo(Long dollNo) { this.dollNo = dollNo;}

    public int getDollType() {
        return dollType;
    }

    public void setDollType(int dollType) {
        this.dollType = dollType;
    }

    public void setDollLikeable(int dollLikeable) {
        this.dollLikeable = dollLikeable;
    }

    public DollReadRes(Long dollNo, int dollLikeable, int dollType){
        this.dollNo = dollNo;
        this.dollLikeable = dollLikeable;
        this.dollType = dollType;
    }


}

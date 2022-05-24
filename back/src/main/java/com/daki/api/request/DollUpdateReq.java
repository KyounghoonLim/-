package com.daki.api.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;

@ApiModel
public class DollUpdateReq {

    private long userNo;
    private int dollLikeable;
    private int dollType;

    public long getUserNo() {return userNo;}

    public int getDollLikeable() {
        return dollLikeable;
    }

    @JsonIgnore
    public void setUserNo(long userNo) {this.userNo = userNo;}

    public void setDollLikeable(int dollLikeable) {
        this.dollLikeable = dollLikeable;
    }

    public int getDollType() {
        return dollType;
    }

    public void setDollType(int dollType) {
        this.dollType = dollType;
    }

    public DollUpdateReq(){}

    public DollUpdateReq(long userNo, int dollLikeable, int dollType){
        this.userNo = userNo;
        this.dollLikeable = dollLikeable;
        this.dollType = dollType;
    }

}

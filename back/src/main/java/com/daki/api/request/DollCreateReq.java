package com.daki.api.request;

import io.swagger.annotations.ApiModel;

@ApiModel
public class DollCreateReq {

    private int dollLikeable;

    private String userNickname;

    private int dollType;

    public int getDollLikeable() {
        return dollLikeable;
    }

    public int getDollType() {
        return dollType;
    }

    public void setDollType(int dollType) {
        this.dollType = dollType;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public DollCreateReq(int dollLikeable, String userNickname, int dollType) {
        this.dollLikeable = dollLikeable;
        this.userNickname = userNickname;
        this.dollType = dollType;
    }

}

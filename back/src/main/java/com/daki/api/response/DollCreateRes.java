package com.daki.api.response;

import io.swagger.annotations.ApiModel;

@ApiModel

public class DollCreateRes {

    private Long dollNo;

    private int dollLikeable;

    private int dollType;

    public DollCreateRes(Long dollNo, int dollLikeable, int dollType) {
        this.dollNo = dollNo;
        this.dollLikeable = dollLikeable;
        this.dollType = dollType;
    }

}

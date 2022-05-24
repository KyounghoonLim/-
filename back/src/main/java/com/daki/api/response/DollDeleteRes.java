package com.daki.api.response;

import com.daki.db.entity.Doll;
import io.swagger.annotations.ApiModel;

@ApiModel

public class DollDeleteRes {

    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DollDeleteRes(String msg){
        this.msg = msg;
    }

}

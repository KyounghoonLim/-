package com.daki.api.service;

import com.daki.api.request.DollCreateReq;
import com.daki.api.request.DollLikeableUpdateReq;
import com.daki.api.request.DollUpdateReq;
import com.daki.api.response.*;

public interface DollService {

    DollReadRes readDollInfo(Long dollNo);
    DollUpdateRes updateDollInfo(DollUpdateReq dollUpdateReq);
    DollDeleteRes deleteDoll(Long dollNo);
    DollLikeableUpdateRes updateDollLikeable(DollLikeableUpdateReq dollLikeableUpdateReq);

}

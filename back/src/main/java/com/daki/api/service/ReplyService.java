package com.daki.api.service;

import com.daki.api.request.ReplyRegistReqDto;
import com.daki.db.entity.Reply;
import com.daki.db.entity.User;

public interface ReplyService {

    public Long regist(ReplyRegistReqDto reqDto, Long boardNo, User user);
}

package com.daki.api.request.diary;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import org.json.simple.JSONObject;

@Getter
@Setter
@ApiModel
public class TestReq {
    JSONObject jsonObject;
}

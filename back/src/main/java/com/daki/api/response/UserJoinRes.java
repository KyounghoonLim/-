package com.daki.api.response;


import com.daki.db.entity.User;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@ApiModel("UserResponse")
@Getter
@Setter
public class UserJoinRes {

    private String email;
    private String nickName;
    private String password;
    private String birth;
    private String Gender;
    private int dollType;

    public static UserJoinRes of(User user, int dollType) {
        UserJoinRes userJoinRes = new UserJoinRes();

        userJoinRes.setEmail(user.getUserEmail());
        userJoinRes.setNickName(user.getUserNickname());
        userJoinRes.setPassword(user.getUserPassword());
        userJoinRes.setBirth(user.getUserBirth());
        userJoinRes.setGender(user.getUserGender());
        userJoinRes.setDollType(dollType);

        return userJoinRes;
    }
}

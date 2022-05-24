package com.daki.db.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_no")
    private Long userNo;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "user_nickname")
    private String userNickname;

    @Column(name = "user_password")
    private String userPassword;

    @Column(name = "user_birth")
    private String userBirth;

    @Column(name = "user_gender")
    private String userGender;  //남자 = "M", 여자 = "F"

//    @Column(name = "user_oauth2")
//    private Oauth2 oauth2;

    @Column(name = "user_point")
    private int userPoint;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    public User(){}

    public void modify(String password, String nickName){
        this.userNickname = nickName;
        this.userPassword = password;
    }

    @Builder
    public User(String userEmail, String userNickname, String userPassword, String userBirth, String userGender, int userPoint, Authority authority) {
        this.userEmail = userEmail;
        this.userNickname = userNickname;
        this.userPassword = userPassword;
        this.userBirth = userBirth;
        this.userGender = userGender;
        this.userPoint = userPoint;
        this.authority = authority;
    }

    public User(Long userNo, String userEmail, String userNickname, String userPassword, String userBirth, String userGender, int userPoint) {
        this.userNo = userNo;
        this.userEmail = userEmail;
        this.userNickname = userNickname;
        this.userPassword = userPassword;
        this.userBirth = userBirth;
        this.userGender = userGender;
        this.userPoint = userPoint;
    }

    @Override
    public String toString() {
        return "User{" +
                "userNo=" + userNo +
                ", userEmail='" + userEmail + '\'' +
                ", userNickname='" + userNickname + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userBirth='" + userBirth + '\'' +
                ", userGender='" + userGender + '\'' +
                ", userPoint=" + userPoint +
                ", authority=" + authority +
                '}';
    }
}

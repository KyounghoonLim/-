package com.daki.db.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "doll")
public class Doll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doll_no")
    private Long dollNo;

    @Column(name = "doll_likeable")
    private int dollLikeable;

    @OneToOne
    @JoinColumn(name = "user_no")
    private User user;

    @Column(name = "doll_type")
    private int dollType;

    public Doll(){}

    public Doll(int dollLikeable, User user, int dollType) {
        this.dollLikeable = dollLikeable;
        this.user = user;
        this.dollType = dollType;
    }

    public Doll(Long dollNo, int dollLikeable, User user, int dollType) {
        this.dollNo = dollNo;
        this.dollLikeable = dollLikeable;
        this.user = user;
        this.dollType = dollType;
    }

    @Override
    public String toString() {
        return "Doll{" +
                "dollNo=" + dollNo +
                ", dollLikeable=" + dollLikeable +
                ", user=" + user +
                ", skin=" + dollType +
                '}';
    }
}

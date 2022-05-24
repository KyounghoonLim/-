package com.daki.db.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "user_item")
public class UserItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_item_no")
    private Long userItemNo;

    @Column(name = "wear_flag")
    private int wearFlag;

    @ManyToOne
    @JoinColumn(name = "doll_no")
    private Doll doll;

    @ManyToOne
    @JoinColumn(name = "item_no")
    private Item item;

    public UserItem(){}

    public UserItem(int wearFlag, Doll doll, Item item) {
        this.wearFlag = wearFlag;
        this.doll = doll;
        this.item = item;
    }

    public UserItem(Long userItemNo, int wearFlag, Doll doll, Item item) {
        this.userItemNo = userItemNo;
        this.wearFlag = wearFlag;
        this.doll = doll;
        this.item = item;
    }

    @Override
    public String toString() {
        return "UserItem{" +
                "userItemNo=" + userItemNo +
                ", wearFlag=" + wearFlag +
                ", doll=" + doll +
                ", item=" + item +
                '}';
    }
}

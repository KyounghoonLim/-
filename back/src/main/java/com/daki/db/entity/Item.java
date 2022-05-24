package com.daki.db.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_no")
    private Long itemNo;

//    @Enumerated(EnumType.STRING)
    @Column(name = "item_categories")
    private int itemCategories;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "item_image")
    private String itemImage;

    @Column(name = "item_price")
    private int itemPrice;

    public Item(){}

    public Item(int itemCategories, String itemName, String itemImage, int itemPrice) {
        this.itemCategories = itemCategories;
        this.itemName = itemName;
        this.itemImage = itemImage;
        this.itemPrice = itemPrice;
    }
}

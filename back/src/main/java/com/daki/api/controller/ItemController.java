package com.daki.api.controller;

import com.daki.api.response.ItemListRes;
import com.daki.api.response.ItemReadResInterface;
import com.daki.api.service.ItemService;
import com.daki.db.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/item")
public class ItemController {

    @Autowired
    ItemService itemService;

    @GetMapping("/info/{dollNo}")
    ResponseEntity<List<ItemListRes>> readAllItemInfo(@PathVariable Long dollNo){
        return ResponseEntity.ok(itemService.readAllItem(dollNo));
    }

}
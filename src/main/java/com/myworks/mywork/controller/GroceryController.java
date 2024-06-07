package com.myworks.mywork.controller;

import com.myworks.mywork.models.GroceryItem;
import com.myworks.mywork.response.BaseResponse;
import com.myworks.mywork.services.GroceryService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/grocery")
public class GroceryController {
    private  final GroceryService groceryService;

    @Autowired
    public GroceryController(GroceryService groceryService) {
        this.groceryService = groceryService;
    }

    @PostMapping("/create")
    public ResponseEntity<BaseResponse> createGrocery(@Valid @RequestBody GroceryItem groceryItem){
        return  new ResponseEntity<>(BaseResponse.success(this.groceryService.createGrocery(groceryItem)), HttpStatus.OK);
    }
}

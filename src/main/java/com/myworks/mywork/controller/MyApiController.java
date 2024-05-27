package com.myworks.mywork.controller;


import com.myworks.mywork.models.Todo;
import com.myworks.mywork.response.BaseResponse;
import com.myworks.mywork.services.MyApiService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/todos")
public class MyApiController {
    private final MyApiService myApiService;


    @Autowired
    public MyApiController(MyApiService myApiService) {
        this.myApiService = myApiService;
    }




    @PostMapping("/create")
    public ResponseEntity<BaseResponse> createTodo(@RequestBody @Valid Todo todo) {
        Todo createdTodo = myApiService.createTodo(todo);
        return new ResponseEntity<>(BaseResponse.success(createdTodo), HttpStatus.CREATED);
    }
}

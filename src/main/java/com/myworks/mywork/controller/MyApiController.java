package com.myworks.mywork.controller;


import com.myworks.mywork.models.Todo;
import com.myworks.mywork.response.BaseResponse;
import com.myworks.mywork.services.MyApiService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/todos")
public class MyApiController {
    private final MyApiService myApiService;


    @Autowired
    public MyApiController(MyApiService myApiService) {
        this.myApiService = myApiService;
    }

    @PutMapping("/updateById/{id}")
    public ResponseEntity<BaseResponse<Todo>> updateTodoById(@PathVariable("id") @Valid @NotNull UUID id, @RequestBody @Valid Todo todo) {
        return new ResponseEntity<BaseResponse<Todo>>(BaseResponse.success(myApiService.updateTodoById(id, todo)), HttpStatus.OK);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<BaseResponse<Boolean>> deleteTodoById(@PathVariable("id") @Valid @NotNull UUID id) {
        return new ResponseEntity<BaseResponse<Boolean>>(BaseResponse.success(myApiService.deleteTodoById(id)), HttpStatus.OK);
    }

    @GetMapping("/getTodosByName")
    public ResponseEntity<BaseResponse<List<Todo>>> getTodosByName(@RequestParam @Valid @NotNull @NotEmpty String text) {
        return new ResponseEntity<BaseResponse<List<Todo>>>(BaseResponse.success(myApiService.getTodosByName(text)), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<Todo>> getById(@PathVariable("id") @Valid @NotNull UUID id) {
        return new ResponseEntity<BaseResponse<Todo>>(BaseResponse.success(myApiService.getTodoById(id)), HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<BaseResponse<List<Todo>>> getAllTodos() {
        List<Todo> todos = myApiService.getTodos();
        return new ResponseEntity<BaseResponse<List<Todo>>>(BaseResponse.success(todos), HttpStatus.OK);

    }


    @PostMapping("/create")
    public ResponseEntity<BaseResponse<Todo>> createTodo(@RequestBody @Valid Todo todo) {
        Todo createdTodo = myApiService.createTodo(todo);
        return new ResponseEntity<BaseResponse<Todo>>(BaseResponse.success(createdTodo), HttpStatus.CREATED);
    }
}

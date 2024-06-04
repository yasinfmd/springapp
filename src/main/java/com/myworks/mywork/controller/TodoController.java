package com.myworks.mywork.controller;


import com.myworks.mywork.annotations.ValidImage;
import com.myworks.mywork.dto.request.CreateTodoTagDTO;
import com.myworks.mywork.dto.request.TodoDTO;
import com.myworks.mywork.dto.response.TodoListDTO;
import com.myworks.mywork.dto.response.TodoTagsDTO;
import com.myworks.mywork.dto.response.TodoWithFilesDTO;
import com.myworks.mywork.models.Tag;
import com.myworks.mywork.models.Todo;
import com.myworks.mywork.response.BasePaginationResponse;
import com.myworks.mywork.response.BaseResponse;
import com.myworks.mywork.services.TodoService;
import com.myworks.mywork.utils.MessageHelper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.bridge.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/todos")
@CrossOrigin(origins = "*")
public class TodoController {
    private final TodoService todoService;
    private final MessageSource messageSource;
    @Autowired
    public TodoController(TodoService todoService, MessageSource messageSource) {
        this.todoService = todoService;
        this.messageSource=messageSource;
    }

    @PutMapping("/updateById/{id}")
    public ResponseEntity<BaseResponse<Todo>> updateTodoById(@PathVariable("id") @Valid @NotNull UUID id, @RequestBody @Valid Todo todo) {
        return new ResponseEntity<BaseResponse<Todo>>(BaseResponse.success(todoService.updateTodoById(id, todo)), HttpStatus.OK);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<BaseResponse<Boolean>> deleteTodoById(@PathVariable("id") @Valid @NotNull UUID id) {
        return new ResponseEntity<BaseResponse<Boolean>>(BaseResponse.success(todoService.deleteTodoById(id)), HttpStatus.OK);
    }

    @GetMapping("/getTodosByName")
    public ResponseEntity<BaseResponse<List<Todo>>> getTodosByName(@RequestParam @Valid @NotNull @NotEmpty String text) {
        return new ResponseEntity<BaseResponse<List<Todo>>>(BaseResponse.success(todoService.getTodosByName(text)), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<Todo>> getById(@PathVariable("id") @Valid @NotNull UUID id) {
        return new ResponseEntity<BaseResponse<Todo>>(BaseResponse.success(todoService.getTodoById(id)), HttpStatus.OK);
    }

    @GetMapping("/{id}/files")
    public ResponseEntity<BaseResponse<TodoWithFilesDTO>> getTodoByIdWithFiles(@PathVariable("id") @Valid @NotNull UUID id) {
        return new ResponseEntity<BaseResponse<TodoWithFilesDTO>>(BaseResponse.success(todoService.getTodoWithFiles(id)), HttpStatus.OK);
    }


    @GetMapping("/list")
    public ResponseEntity<BaseResponse<List<TodoListDTO>>> getAllTodos(@RequestParam Optional<String> sortBy,
                                                                       @RequestParam Optional<String> sortDirection) {
        return new ResponseEntity<BaseResponse<List<TodoListDTO>>>(BaseResponse.success(todoService.getTodos(sortDirection, sortBy)), HttpStatus.OK);

    }

    @GetMapping("/pageable")
    public ResponseEntity<BasePaginationResponse> getAllTodosWithPagination(@RequestParam Optional<String> sortBy,
                                                                                               @RequestParam Optional<String> sortDirection, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "1") int size) {
        return new ResponseEntity<BasePaginationResponse>(todoService.getTodosWithPagination(sortDirection, sortBy, page, size), HttpStatus.OK);
    }

    @PostMapping("/file")
    public ResponseEntity createfile(@ValidImage @RequestPart("image") MultipartFile file) {
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<BaseResponse<TodoListDTO>> createTodo(@RequestBody @Valid TodoDTO todo) {
        return new ResponseEntity<BaseResponse<TodoListDTO>>(BaseResponse.success(todoService.createTodo(todo)), HttpStatus.CREATED);
    }

    @PostMapping("/createTag")
    public Object createTodo(@RequestBody @Valid CreateTodoTagDTO todoTagDTO) {
      /*  long g=20;
        var a= this.studentService.listStudentCourses(g);
       return  a;*/
        return new ResponseEntity<>(BaseResponse.success(todoService.addTagToTodo(todoTagDTO)), HttpStatus.CREATED);
    }

    @GetMapping("/{id}/tags")
    public ResponseEntity<BaseResponse<List<TodoTagsDTO>>> getTodoTags(@PathVariable UUID id) {
        return new ResponseEntity<BaseResponse<List<TodoTagsDTO>>>(BaseResponse.success(todoService.getTodoTags(id)), HttpStatus.OK);
    }

    /*
    @GetMapping("/getTodosByUserId/{id}")
    public ResponseEntity<BaseResponse<List<TodoListDTO>>> getTodoById(@PathVariable("id") @Valid @NotNull UUID id){
        return  new ResponseEntity<BaseResponse<List<TodoListDTO>>>(BaseResponse.success(todoService.getTodosByUserId(id)), HttpStatus.OK);
    }

     */

}

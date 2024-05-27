package com.myworks.mywork.services;

import com.myworks.mywork.models.Todo;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

public interface MyApiService {


    List<Todo> getTodosByName(String text);
    Boolean deleteTodoById(UUID uuid);
    Todo updateTodoById(UUID uuid, Todo todo);
    Todo getTodoById(UUID uuid);
    List<Todo> getTodos();
    Todo createTodo(Todo todo);


}

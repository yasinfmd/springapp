package com.myworks.mywork.services;

import com.myworks.mywork.dto.request.TodoDTO;
import com.myworks.mywork.dto.response.TodoListDTO;
import com.myworks.mywork.models.Todo;

import java.util.List;
import java.util.UUID;

public interface TodoService {


    List<Todo> getTodosByName(String text);
    Boolean deleteTodoById(UUID uuid);
    Todo updateTodoById(UUID uuid, Todo todo);
    Todo getTodoById(UUID uuid);
    List<TodoListDTO> getTodos();


    TodoListDTO createTodo(TodoDTO dto);

    List<TodoListDTO> getTodosByUserId(UUID id);
}

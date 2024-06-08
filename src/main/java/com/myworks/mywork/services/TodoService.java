package com.myworks.mywork.services;

import com.myworks.mywork.dto.request.CreateTodoTagDTO;
import com.myworks.mywork.dto.request.TodoDTO;
import com.myworks.mywork.dto.response.TodoListDTO;
import com.myworks.mywork.dto.response.TodoTagsDTO;
import com.myworks.mywork.dto.response.TodoWithFilesDTO;
import com.myworks.mywork.models.Tag;
import com.myworks.mywork.models.Todo;
import com.myworks.mywork.response.BasePaginationResponse;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TodoService {


    TodoWithFilesDTO getTodoWithFiles(UUID uuid);

    List<Todo> getTodosByName(String text);

    Boolean deleteTodoById(UUID uuid);

    Todo updateTodoById(UUID uuid, Todo todo);

    Todo getTodoById(UUID uuid);

    List<TodoListDTO> getTodos(Optional<String> sortDirection, Optional<String> sortBy);



    BasePaginationResponse getTodosWithPagination(Optional<String> sortDirection, Optional<String> sortBy , int page, int pageSize);


    TodoListDTO createTodo(TodoDTO dto);

    Boolean addTagToTodo(CreateTodoTagDTO dto);

    List<TodoTagsDTO> getTodoTags(UUID id);

    //  List<TodoListDTO> getTodosByUserId(UUID id);
}

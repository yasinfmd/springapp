package com.myworks.mywork.services;

import com.myworks.mywork.models.Todo;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyApiService {

    public Todo createTodo(Todo todo){
        return  todo;
    }

    public List<Todo> getAllTodos(){
            return List.of();
    }


}

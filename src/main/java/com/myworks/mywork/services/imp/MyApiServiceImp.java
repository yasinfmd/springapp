package com.myworks.mywork.services.imp;

import com.myworks.mywork.exception.RecordNotFoundException;
import com.myworks.mywork.models.Todo;
import com.myworks.mywork.repository.TodoRepository;
import com.myworks.mywork.services.MyApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class MyApiServiceImp implements MyApiService {
    private final TodoRepository todoRepository;

    @Autowired
    public MyApiServiceImp(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public List<Todo> getTodosByName(String text) {
        log.info("Find todos by text : " + text);
        return todoRepository.findByTextContaining(text);
    }

    @Override
    public Boolean deleteTodoById(UUID uuid) {
        log.info("Find todo by id : " + uuid);
        todoRepository.deleteById(uuid);
        return true;
    }

    @Override
    public Todo updateTodoById(UUID uuid, Todo todo) {
        log.info("Find todo by id : " + uuid);
        Todo updateTodo = todoRepository.findById(uuid).orElseThrow(() -> new RecordNotFoundException("Todo not found"));
        log.info("Create Todo  with params" + String.valueOf(todo));
        return todoRepository.save(todo);
    }

    @Override
    public Todo getTodoById(UUID uuid) {
        log.info("Find todo by id : " + uuid);
        return todoRepository.findById(uuid).orElseThrow(()->    new RecordNotFoundException("Todo not found"));
    }

    @Override
    public List<Todo> getTodos() {
        log.info("Todo List access");
        return todoRepository.findAll();
    }

    @Override
    @Transactional
    public Todo createTodo(Todo todo) {
        log.info("Create Todo  with params" + String.valueOf(todo));
        try {
            return todoRepository.save(todo);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}

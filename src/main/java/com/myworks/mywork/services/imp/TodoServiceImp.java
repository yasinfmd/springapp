package com.myworks.mywork.services.imp;

import com.myworks.mywork.dto.request.TodoDTO;
import com.myworks.mywork.dto.request.TodoDetailDTO;
import com.myworks.mywork.exception.RecordNotFoundException;
import com.myworks.mywork.models.Todo;
import com.myworks.mywork.models.TodoDetail;
import com.myworks.mywork.models.User;
import com.myworks.mywork.repository.TodoRepository;
import com.myworks.mywork.services.TodoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class TodoServiceImp implements TodoService {
    private final TodoRepository todoRepository;

    @Autowired
    public TodoServiceImp(TodoRepository todoRepository) {
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
        updateTodo.setText(todo.getText());
        updateTodo.setTitle(todo.getTitle());
        updateTodo.setCompleted(todo.getCompleted());
        log.info("Create Todo  with params" + String.valueOf(todo));
        return todoRepository.save(updateTodo);
    }

    @Override
    public Todo getTodoById(UUID uuid) {
        log.info("Find todo by id : " + uuid);
        return todoRepository.findById(uuid).orElseThrow(()->    new RecordNotFoundException("Todo not found"));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Todo> getTodos() {
        log.info("Todo List access");
        return todoRepository.findAll();
    }

    @Override
    @Transactional
    public Todo createTodo(TodoDTO dto) {
        log.info("Create Todo  with params" + String.valueOf(dto));
        try {
            Todo todo = new Todo();
            todo.setTitle(dto.title());
            todo.setText(dto.text());
            todo.setCompleted(dto.completed());
            TodoDetail todoDetail=new TodoDetail();
            TodoDetailDTO todoDetailDTO = dto.todoDetail();
            todoDetail.setDetail(todoDetailDTO.detail());
            todo.setTodoDetail(todoDetail);
            User user = new User();
            user.setId(dto.userId());
            todo.setUser(user);

            return todoRepository.save(todo);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}

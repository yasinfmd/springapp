package com.myworks.mywork.services.imp;

import com.myworks.mywork.dto.request.TodoDTO;
import com.myworks.mywork.dto.request.TodoDetailDTO;
import com.myworks.mywork.dto.response.TodoDetailListDTO;
import com.myworks.mywork.dto.response.TodoListDTO;
import com.myworks.mywork.dto.response.UserListDTO;
import com.myworks.mywork.exception.RecordNotFoundException;
import com.myworks.mywork.models.Todo;
import com.myworks.mywork.models.TodoDetail;
import com.myworks.mywork.models.User;
import com.myworks.mywork.repository.TodoRepository;
import com.myworks.mywork.response.BaseResponse;
import com.myworks.mywork.services.FileService;
import com.myworks.mywork.services.TodoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TodoServiceImp implements TodoService {
    private final TodoRepository todoRepository;
    private final FileService fileService;

    @Autowired
    public TodoServiceImp(TodoRepository todoRepository, FileService fileService ) {
        this.todoRepository = todoRepository;
        this.fileService=fileService;
    }

    public  void test(MultipartFile file){
        this.fileService.store(file);
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
    @Transactional
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
        return todoRepository.findById(uuid).orElseThrow(() -> new RecordNotFoundException("Todo not found"));
    }

    @Transactional(readOnly = true)
    @Override
    public List<TodoListDTO> getTodos() {
        log.info("Todo List access");
        return todoRepository.findAll().stream().map((todo -> new TodoListDTO(todo.getId(), todo.getVersion(), todo.getTitle(), todo.getText(), todo.getCompleted(), new TodoDetailListDTO(todo.getTodoDetail().getId(), todo.getTodoDetail().getVersion(), todo.getTodoDetail().getDetail())))).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public TodoListDTO createTodo(TodoDTO dto) {
        log.info("Create Todo  with params" + String.valueOf(dto));
        try {
            Todo todo = new Todo();
            todo.setTitle(dto.title());
            todo.setText(dto.text());
            todo.setCompleted(dto.completed());
            TodoDetail todoDetail = new TodoDetail();
            TodoDetailDTO todoDetailDTO = dto.todoDetail();
            todoDetail.setDetail(todoDetailDTO.detail());
            todo.setTodoDetail(todoDetail);
            User user = new User();
            user.setId(dto.userId());
            todo.setUser(user);
            Todo savedTodo = todoRepository.save(todo);
            return new TodoListDTO(savedTodo.getId(), savedTodo.getVersion(), savedTodo.getTitle(), savedTodo.getText(), savedTodo.getCompleted(), new TodoDetailListDTO(savedTodo.getTodoDetail().getId(), savedTodo.getTodoDetail().getVersion(), savedTodo.getTodoDetail().getDetail()));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<TodoListDTO> getTodosByUserId(UUID id) {
        List<Todo> todoList = todoRepository.findByUserId(id);
        return todoList.stream().map((todo -> new TodoListDTO(todo.getId(), todo.getVersion(), todo.getTitle(), todo.getText(), todo.getCompleted(), new TodoDetailListDTO(todo.getTodoDetail().getId(), todo.getTodoDetail().getVersion(), todo.getTodoDetail().getDetail())))).collect(Collectors.toList());
    }
}

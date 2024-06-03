package com.myworks.mywork.services.imp;

import com.myworks.mywork.dto.request.CreateTodoTagDTO;
import com.myworks.mywork.dto.request.TodoDTO;
import com.myworks.mywork.dto.request.TodoDetailDTO;
import com.myworks.mywork.dto.response.TodoDetailListDTO;
import com.myworks.mywork.dto.response.TodoListDTO;
import com.myworks.mywork.dto.response.TodoTagsDTO;
import com.myworks.mywork.dto.response.TodoWithFilesDTO;
import com.myworks.mywork.exception.RecordNotFoundException;
import com.myworks.mywork.models.Tag;
import com.myworks.mywork.models.Todo;
import com.myworks.mywork.models.TodoDetail;
import com.myworks.mywork.models.User;
import com.myworks.mywork.repository.TagRepository;
import com.myworks.mywork.repository.TodoRepository;
import com.myworks.mywork.response.BasePaginationResponse;
import com.myworks.mywork.services.FileService;
import com.myworks.mywork.services.TodoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TodoServiceImp implements TodoService {
    private final TodoRepository todoRepository;
    private final TagRepository tagRepository;

    @Autowired
    public TodoServiceImp(TodoRepository todoRepository, TagRepository tagRepository) {
        this.todoRepository = todoRepository;
        this.tagRepository = tagRepository;
    }


    @Override
    @Transactional(readOnly = true)
    public TodoWithFilesDTO getTodoWithFiles(UUID uuid) {
        log.info("Find todos by id : " + uuid);
        Todo todo = todoRepository.findById(uuid)
                .orElseThrow(() -> new RecordNotFoundException("Todo not found with id " + uuid));
        List<Map<String, String>> files = todo.getFiles().stream().map((f) -> {
            Map<String, String> fileMap = new HashMap<>();
            fileMap.put("id", f.getId().toString());
            fileMap.put("url", f.getFileUrl());
            return fileMap;
        }).toList();


        return new TodoWithFilesDTO(todo.getId(), files);

    }

    @Override
    public List<Todo> getTodosByName(String text) {
        log.info("Find todos by text : " + text);
        return todoRepository.findByTextContaining(text);
    }

    @Override
    public Boolean deleteTodoById(UUID uuid) {
        log.info("Find todo by id : " + uuid);
        Todo updateTodo = todoRepository.findById(uuid).orElseThrow(() -> new RecordNotFoundException("Todo not found"));
        updateTodo.setDeleted(true);
        todoRepository.save(updateTodo);
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
    public List<TodoListDTO> getTodos(Optional<String> sortDirection, Optional<String> sortBy) {
        log.info("Todo List access");
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection.orElse("asc")), sortBy.orElse("title"));
        return todoRepository.findAll(sort).stream().map((todo -> new TodoListDTO(todo.getId(), todo.getVersion(), todo.getTitle(), todo.getText(), todo.getCompleted(), new TodoDetailListDTO(todo.getTodoDetail().getId(), todo.getTodoDetail().getVersion(), todo.getTodoDetail().getDetail())))).collect(Collectors.toList());
    }

    @Override
    public BasePaginationResponse getTodosWithPagination(Optional<String> sortDirection, Optional<String> sortBy, int page, int size) {
        Pageable paging = PageRequest.of(page, size);
        Page<Todo> todoPage = todoRepository.findAll(paging);
        List<TodoListDTO> todoListDto = todoPage.getContent().stream().map((todo -> new TodoListDTO(todo.getId(), todo.getVersion(), todo.getTitle(), todo.getText(), todo.getCompleted(), new TodoDetailListDTO(todo.getTodoDetail().getId(), todo.getTodoDetail().getVersion(), todo.getTodoDetail().getDetail())))).collect(Collectors.toList());
        return BasePaginationResponse.success(todoListDto, todoPage.getNumber(), todoPage.getSize(), todoPage.getTotalElements());
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
           /* User user = new User();
            user.setId(dto.userId());
            todo.setUser(user);
            */
            Todo savedTodo = todoRepository.save(todo);
            return new TodoListDTO(savedTodo.getId(), savedTodo.getVersion(), savedTodo.getTitle(), savedTodo.getText(), savedTodo.getCompleted(), new TodoDetailListDTO(savedTodo.getTodoDetail().getId(), savedTodo.getTodoDetail().getVersion(), savedTodo.getTodoDetail().getDetail()));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Boolean addTagToTodo(CreateTodoTagDTO dto) {
        Optional<Todo> optionalTodo = todoRepository.findById(UUID.fromString(dto.id()));
        if (optionalTodo.isEmpty()) {
            throw new RuntimeException("Todo not found");
        }
        Todo todo = optionalTodo.get();
        List<Tag> tags = new ArrayList<>();
        for (String name : dto.tags()) {
            Tag tag = new Tag();
            tag.setName(name);
            tags.add(tagRepository.save(tag));
        }
        todo.setTags(tags);


        todoRepository.save(todo);
        return true;
    }

    @Override
    public List<TodoTagsDTO> getTodoTags(UUID id) {
        Todo todo = this.getTodoById(id);
        return todo.getTags().stream().map((tag) -> new TodoTagsDTO(tag.getId(), tag.getName())).toList();
    }

    /*
    @Override
    public List<TodoListDTO> getTodosByUserId(UUID id) {
        List<Todo> todoList = todoRepository.findByUserId(id);
        return todoList.stream().map((todo -> new TodoListDTO(todo.getId(), todo.getVersion(), todo.getTitle(), todo.getText(), todo.getCompleted(), new TodoDetailListDTO(todo.getTodoDetail().getId(), todo.getTodoDetail().getVersion(), todo.getTodoDetail().getDetail())))).collect(Collectors.toList());
    }

     */
}

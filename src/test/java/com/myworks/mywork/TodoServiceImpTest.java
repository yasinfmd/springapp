package com.myworks.mywork;

import com.myworks.mywork.dto.request.TodoDTO;
import com.myworks.mywork.dto.request.TodoDetailDTO;
import com.myworks.mywork.dto.response.TodoListDTO;
import com.myworks.mywork.exception.RecordNotFoundException;
import com.myworks.mywork.models.Todo;
import com.myworks.mywork.models.TodoDetail;
import com.myworks.mywork.repository.TagRepository;
import com.myworks.mywork.repository.TodoRepository;
import com.myworks.mywork.services.imp.TodoServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TodoServiceImpTest {
    @Mock
    private TodoRepository todoRepository;

    @Mock
    private TagRepository tagRepository;

    @Mock
    private CacheManager cacheManager;

    @InjectMocks
    private TodoServiceImp todoServiceImp;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetTodoById_TodoExists() {
        UUID uuid = UUID.randomUUID();
        Todo todo = new Todo();
        todo.setId(uuid);
        todo.setTitle("Test Title");

        when(todoRepository.findById(uuid)).thenReturn(Optional.of(todo));

        Todo result = todoServiceImp.getTodoById(uuid);

        assertNotNull(result);
        assertEquals("Test Title", result.getTitle());
    }

    @Test
    public void testGetTodoById_TodoNotFound() {
        UUID uuid = UUID.randomUUID();

        when(todoRepository.findById(uuid)).thenReturn(Optional.empty());

        assertThrows(RecordNotFoundException.class, () -> {
            todoServiceImp.getTodoById(uuid);
        });
    }

    @Test
    public void testCreateTodo() {
        TodoDTO dto = new TodoDTO("Title", "Text", false, new TodoDetailDTO("Detail"), UUID.randomUUID());
        Todo todo = new Todo();
        todo.setTitle(dto.title());
        todo.setText(dto.text());
        todo.setCompleted(dto.completed());
        TodoDetail todoDetail = new TodoDetail();
        todoDetail.setDetail(dto.todoDetail().detail());
        todo.setTodoDetail(todoDetail);

        Todo savedTodo = new Todo();
        savedTodo.setId(UUID.randomUUID());
        savedTodo.setTitle(dto.title());
        savedTodo.setText(dto.text());
        savedTodo.setCompleted(dto.completed());
        savedTodo.setTodoDetail(todoDetail);

        Cache cache = mock(Cache.class);
        when(cacheManager.getCache("todos")).thenReturn(cache);

        when(todoRepository.save(any(Todo.class))).thenReturn(savedTodo);

        TodoListDTO result = todoServiceImp.createTodo(dto);

        assertNotNull(result);
        assertEquals(dto.title(), result.title());
    }

    @Test
    public void testDeleteTodoById() {
        UUID uuid = UUID.randomUUID();
        Todo todo = new Todo();
        todo.setId(uuid);

        when(todoRepository.findById(uuid)).thenReturn(Optional.of(todo));

        Boolean result = todoServiceImp.deleteTodoById(uuid);

        assertTrue(result);
        verify(todoRepository, times(1)).save(any(Todo.class));
    }

    @Test
    public void testUpdateTodoById() {
        UUID uuid = UUID.randomUUID();
        Todo todo = new Todo();
        todo.setId(uuid);
        todo.setTitle("Old Title");
        todo.setText("Old Text");

        Todo updateData = new Todo();
        updateData.setTitle("New Title");
        updateData.setText("New Text");
        updateData.setCompleted(true);

        when(todoRepository.findById(uuid)).thenReturn(Optional.of(todo));
        when(todoRepository.save(any(Todo.class))).thenReturn(todo);

        Todo result = todoServiceImp.updateTodoById(uuid, updateData);

        assertNotNull(result);
        assertEquals("New Title", result.getTitle());
        assertEquals("New Text", result.getText());
        assertTrue(result.getCompleted());
    }


}

package com.myworks.mywork;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myworks.mywork.controller.TodoController;
import com.myworks.mywork.dto.request.TodoDTO;
import com.myworks.mywork.dto.request.TodoDetailDTO;
import com.myworks.mywork.dto.response.TodoDetailListDTO;
import com.myworks.mywork.dto.response.TodoListDTO;
import com.myworks.mywork.exception.RecordNotFoundException;
import com.myworks.mywork.models.Todo;
import com.myworks.mywork.models.TodoDetail;
import com.myworks.mywork.response.BaseResponse;
import com.myworks.mywork.services.TodoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class TodoControllerTest {
    private static final String ENDPOINT_PATH = "/api/todos/";
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TodoService todoService;

    @Test
    public  void testCreateTodoShouldReturn400BadRequest() throws Exception {
        Todo todo=new Todo();
        todo.setText("");
        todo.setTitle("");
        todo.setTodoDetail(null);
        String requestBody=objectMapper.writeValueAsString(todo);
        ResultActions result = mockMvc.perform(post(ENDPOINT_PATH+"create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
        );
        result.andExpect(status().isBadRequest()).andDo(print())
                .andExpect(result1 -> assertThat(result1.getResolvedException())
                        .isInstanceOf(MethodArgumentNotValidException.class));
    }

    @Test
    public  void testCreateTodoShouldReturn201Created() throws Exception {
        TodoDTO todoDTO=new TodoDTO("Metin1231232","Text123123213",false,new TodoDetailDTO("Detail12312312"), UUID.randomUUID());
        String requestBody=objectMapper.writeValueAsString(todoDTO);
        ResultActions result = mockMvc.perform(post(ENDPOINT_PATH+"create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
        );
        result.andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.id").exists())
                .andExpect(jsonPath("$.data.title").value("Metin1231232"))
                .andExpect(jsonPath("$.message").value("success"))
                .andExpect(jsonPath("$.success").value(true))
                .andDo(print());
    }

    @Test
    public  void testGetShouldReturn404NotFound() throws Exception {
        ResultActions result = mockMvc.perform(get(ENDPOINT_PATH+UUID.randomUUID()).contentType(MediaType.APPLICATION_JSON));
        result.andExpect(status().isNotFound())
                .andExpect(result1 -> assertThat(result1.getResolvedException())
                        .isInstanceOf(RecordNotFoundException.class))
                .andExpect(jsonPath("$.code").value("Not Found"))
                .andExpect(jsonPath("$.message").value("Todo not found"))
                .andExpect(jsonPath("$.success").value(false))
                .andDo(print());
    }


    @Test
    public void testGetAllTodos() throws Exception {
        TodoListDTO todoListDTO=new TodoListDTO(UUID.randomUUID(), 0, "title", "text", true, new TodoDetailListDTO(UUID.randomUUID(), 0, "test"));
        List<TodoListDTO> todos = List.of(todoListDTO);


        when(todoService.getTodos(eq(Optional.of("asc")),eq(Optional.of("title")))).thenReturn(todos);


        // Perform GET Request
        mockMvc.perform(get(ENDPOINT_PATH + "list").param("sortDirection","asc").param("sortBy","title"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data[0].title").value("title"))
                .andExpect(jsonPath("$.data.length()").value(todos.size()))
                .andExpect(jsonPath("$.message").value("success"))
                .andExpect(jsonPath("$.success").value(true))
        ;
        verify(todoService, times(1)).getTodos(eq(Optional.of("asc")), eq(Optional.of("title")));

    }

}

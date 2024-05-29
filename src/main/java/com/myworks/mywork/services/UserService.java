package com.myworks.mywork.services;

import com.myworks.mywork.dto.request.UserDTO;
import com.myworks.mywork.dto.response.TodoListDTO;
import com.myworks.mywork.dto.response.UserListDTO;
import com.myworks.mywork.models.User;

import java.util.List;
import java.util.UUID;

public interface UserService  {
    List<UserListDTO> getUsers();
    Boolean register(UserDTO userDTO);
}

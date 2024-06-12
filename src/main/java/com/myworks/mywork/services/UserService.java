package com.myworks.mywork.services;

import com.myworks.mywork.dto.request.AuthDTO;
import com.myworks.mywork.dto.request.UserDTO;
import com.myworks.mywork.dto.response.TodoListDTO;
import com.myworks.mywork.dto.response.UserListDTO;
import com.myworks.mywork.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<UserListDTO> getUsers();

    Boolean register(UserDTO userDTO);

    String auth(AuthDTO authDTO);

    UserDetailsService userDetailsService();


}

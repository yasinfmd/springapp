package com.myworks.mywork.services;

import com.myworks.mywork.dto.request.AuthDTO;
import com.myworks.mywork.dto.request.SigninRequest;
import com.myworks.mywork.dto.request.UserDTO;
import com.myworks.mywork.dto.response.SigninResponse;
import com.myworks.mywork.dto.response.UserListDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface UserService {
    List<UserListDTO> getUsers();

    Boolean register(UserDTO userDTO);

    SigninResponse auth(SigninRequest signinRequestDTO);





}

package com.myworks.mywork.services;

import com.myworks.mywork.dto.request.UserDTO;
import com.myworks.mywork.models.User;

public interface UserService  {
    Boolean register(UserDTO userDTO);
}

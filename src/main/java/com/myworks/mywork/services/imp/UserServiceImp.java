package com.myworks.mywork.services.imp;

import com.myworks.mywork.dto.request.AuthDTO;
import com.myworks.mywork.dto.request.TodoDTO;
import com.myworks.mywork.dto.request.UserDTO;
import com.myworks.mywork.dto.response.TodoListDTO;
import com.myworks.mywork.dto.response.UserListDTO;
import com.myworks.mywork.exception.RecordNotFoundException;
import com.myworks.mywork.models.Todo;
import com.myworks.mywork.models.User;
import com.myworks.mywork.repository.UserRepository;
import com.myworks.mywork.services.UserService;
import com.myworks.mywork.utils.PasswordHasher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final PasswordHasher passwordHasher;

    @Autowired
    public UserServiceImp(UserRepository userRepository, PasswordHasher passwordHasher) {
        this.userRepository = userRepository;
        this.passwordHasher = passwordHasher;
    }


    @Override
    public List<UserListDTO> getUsers() {
        return userRepository.findAll().stream().map((user -> new UserListDTO(user.getId(), "@"+user.getUsername(), user.getName(), user.getSurname(), user.getFullName(), user.getEmail()))).collect(Collectors.toList());

    }

    @Transactional
    @Override
    public Boolean register(UserDTO dto) {
        log.info("Create User  with params" + String.valueOf(dto));
        try {
            User user = new User();
            user.setName(dto.name());
            user.setUsername(dto.userName());
            user.setSurname(dto.lastName());
            user.setEmail(dto.email());
            user.setFullName();
            user.setPassword(passwordHasher.hashPassword(dto.password()));
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public String auth(AuthDTO authDTO) {
        return "ooo";
    }


}

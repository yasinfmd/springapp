package com.myworks.mywork.services.imp;

import com.myworks.mywork.dto.request.TodoDTO;
import com.myworks.mywork.dto.request.UserDTO;
import com.myworks.mywork.models.Todo;
import com.myworks.mywork.models.User;
import com.myworks.mywork.repository.UserRepository;
import com.myworks.mywork.services.UserService;
import com.myworks.mywork.utils.PasswordHasher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}

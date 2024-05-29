package com.myworks.mywork.services.imp;

import com.myworks.mywork.dto.request.UserDTO;
import com.myworks.mywork.models.User;
import com.myworks.mywork.repository.UserRepository;
import com.myworks.mywork.services.UserService;
import com.myworks.mywork.utils.PasswordHasher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    private  final UserRepository userRepository;
    private  final PasswordHasher passwordHasher;

    @Autowired
    public UserServiceImp(UserRepository userRepository,PasswordHasher passwordHasher) {
        this.userRepository=userRepository;
        this.passwordHasher=passwordHasher;
    }

    @Override
    public User register(UserDTO dto) {
        User user=new User();
        user.setName(dto.name());
        user.setUsername(dto.userName());
        user.setSurname(dto.lastName());
        user.setEmail(dto.email());
        user.setFullName();
        user.setPassword(passwordHasher.hashPassword(dto.password()));
        userRepository.save(user);
        return null;
    }
}

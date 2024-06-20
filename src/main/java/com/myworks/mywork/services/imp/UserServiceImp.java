package com.myworks.mywork.services.imp;

import com.myworks.mywork.dto.request.SigninRequest;
import com.myworks.mywork.dto.request.UserDTO;
import com.myworks.mywork.dto.response.SigninResponse;
import com.myworks.mywork.dto.response.UserListDTO;
import com.myworks.mywork.exception.RecordNotFoundException;
import com.myworks.mywork.exception.UnauthorizedException;
import com.myworks.mywork.models.User;
import com.myworks.mywork.repository.UserRepository;
import com.myworks.mywork.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Lazy
    private final AuthenticationManager authenticationManager;
    @Lazy
    private final JWTServiceImp jwtService;


    @Override
    public List<UserListDTO> getUsers() {
        return userRepository.findAll().stream().map((user -> new UserListDTO(user.getId(), "@" + user.getUsername(), user.getName(), user.getSurname(), user.getFullName(), user.getEmail()))).collect(Collectors.toList());

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
            user.setPassword(this.passwordEncoder.encode(dto.password()));
            user.setRole(dto.role());
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    @Override
    public SigninResponse auth(SigninRequest signinRequestDTO) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequestDTO.username(), signinRequestDTO.password()));
        User user = userRepository.findByUsername(signinRequestDTO.username()).orElseThrow(() -> new UnauthorizedException("Username or password inccorrret"));
        String token = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);


        return new SigninResponse(token, refreshToken, user.getUsername(), user.getFullName());

    }


}

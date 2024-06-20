package com.myworks.mywork.controller;

import com.myworks.mywork.dto.request.SigninRequest;
import com.myworks.mywork.dto.request.UserDTO;
import com.myworks.mywork.dto.response.SigninResponse;
import com.myworks.mywork.dto.response.UserListDTO;
import com.myworks.mywork.response.BaseResponse;
import com.myworks.mywork.services.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/list")
    public ResponseEntity<BaseResponse<List<UserListDTO>>> list() {
        return new ResponseEntity<BaseResponse<List<UserListDTO>>>(BaseResponse.success(userService.getUsers()), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<BaseResponse<Boolean>> register(@RequestBody @Valid UserDTO userDTO) {
        return new ResponseEntity<BaseResponse<Boolean>>(BaseResponse.success(userService.register(userDTO)), HttpStatus.OK);
    }

    @PostMapping("/auth")
    public ResponseEntity<BaseResponse<SigninResponse>> auth(@RequestBody @Valid SigninRequest signinDTO) {
        return new ResponseEntity<BaseResponse<SigninResponse>>(BaseResponse.success(userService.auth(signinDTO)), HttpStatus.OK);
    }
}

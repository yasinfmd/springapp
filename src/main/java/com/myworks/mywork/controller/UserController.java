package com.myworks.mywork.controller;

import com.myworks.mywork.dto.request.UserDTO;
import com.myworks.mywork.models.User;
import com.myworks.mywork.response.BaseResponse;
import com.myworks.mywork.services.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController {
    private  final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService=userService;
    }

    @PostMapping("/create")
    public ResponseEntity<BaseResponse<User>> register(@RequestBody @Valid UserDTO userDTO){
        return  new ResponseEntity<BaseResponse<User>>(BaseResponse.success(userService.register(userDTO)), HttpStatus.OK);
    }
}

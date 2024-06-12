package com.myworks.mywork.dto.request;

import com.myworks.mywork.annotations.PasswordValidation;
import com.myworks.mywork.annotations.UniqueUser;
import jakarta.validation.constraints.*;

public record UserDTO(
        @NotNull(message = "User name cannot be null")
        @NotEmpty(message = "User name cannot be empty")
        @UniqueUser(message = "User exist", fieldName = "username")
        String userName,
        @NotNull(message = "Name  cannot be null")
        @NotEmpty(message = "Name  cannot be empty")
        String name,
        @NotNull(message = "Surname  cannot be null")
        @NotEmpty(message = "Surname  cannot be empty")
        String lastName,
        @NotNull(message = "Email  cannot be null")
        @NotEmpty(message = "Email  cannot be empty")
        @Email(message = "Email is not correct format")
        @UniqueUser(message = "User exist", fieldName = "email")
        String email,
        @NotNull(message = "Password  cannot be null")
        @NotEmpty(message = "Password  cannot be empty")
        @NotBlank(message = "Password cannot be blank")
        @Size(min = 8)
        @PasswordValidation
        String password

) {
}

package com.myworks.mywork.dto.request;

import com.myworks.mywork.annotations.PasswordValidation;
import com.myworks.mywork.annotations.UniqueEmail;
import jakarta.validation.constraints.*;

public record UserDTO(
        @NotNull(message = "User name cannot be null")
        @NotEmpty(message = "User name cannot be empty")
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
        @UniqueEmail(message = "User exist")
        String email,
        @NotNull(message = "Password  cannot be null")
        @NotEmpty(message = "Password  cannot be empty")
        @NotBlank(message = "Password cannot be blank")
        @Size(min = 8)
        @PasswordValidation
        String password

) {
}

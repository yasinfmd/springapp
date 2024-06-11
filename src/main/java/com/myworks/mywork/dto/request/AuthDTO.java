package com.myworks.mywork.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record AuthDTO(@NotNull @Email String email,@NotNull String password) {
}

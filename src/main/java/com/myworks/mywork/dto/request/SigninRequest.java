package com.myworks.mywork.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record SigninRequest(@NotNull String username, @NotNull @Size(min = 8) String password) {
}

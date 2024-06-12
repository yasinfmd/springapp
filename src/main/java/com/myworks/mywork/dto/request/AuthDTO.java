package com.myworks.mywork.dto.request;

import jakarta.validation.constraints.NotNull;

public record AuthDTO(@NotNull  String username,@NotNull String password) {
}

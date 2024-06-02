package com.myworks.mywork.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record CreateTodoTagDTO(@NotNull String id, @NotNull List<String> tags) {
}

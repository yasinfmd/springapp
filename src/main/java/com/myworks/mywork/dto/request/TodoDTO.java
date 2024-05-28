package com.myworks.mywork.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

public record TodoDTO(
        @NotNull(message = "Title Cannot be null")
        @Size(min = 10,max = 150,message = "Title Min 3 Max 150")
        @NotEmpty(message = "Title Cannot be empty")
        String title,

        @NotNull(message = "Cannot be null")
        @Size(min = 5,max = 150,message = "Min 5 Max 150")
        String text,

        @NotNull(message = "Completed Cannot be null")
        Boolean completed,

        @NotNull(message = "TodoDetail cannot be null")
        @Valid
        TodoDetailDTO todoDetail
) {}
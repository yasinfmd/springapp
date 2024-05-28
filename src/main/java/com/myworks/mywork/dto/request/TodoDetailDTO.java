package com.myworks.mywork.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public record TodoDetailDTO(
        @NotNull(message = "Detail Cannot be null")
        @NotEmpty(message = "Detail Cannot be empty")
        String detail
) {}
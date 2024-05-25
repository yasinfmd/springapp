package com.myworks.mywork.models;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Todo {
    @NotNull(message = "Cannot be null")
    @Size(min = 10,max = 150,message = "Min 10 Max 150")
    private String title;
    @NotNull(message = "Cannot be null")
    @Size(min = 5,max = 150,message = "Min 5 Max 150")
    private String text;
}

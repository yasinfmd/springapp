package com.myworks.mywork.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Todo extends BaseEntity {
    @NotNull(message = "Cannot be null")
    @Size(min = 10,max = 150,message = "Min 10 Max 150")
    @NotEmpty(message = "Boş Bıraklıamaz")
    private String title;
    @NotNull(message = "Cannot be null")
    @Size(min = 5,max = 150,message = "Min 5 Max 150")
    private String text;
}

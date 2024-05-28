package com.myworks.mywork.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TodoDetail extends  BaseEntity {
    @NotNull(message = "Not alanı boş olamaz")
    @Size(min = 1, max = 255, message = "Not 1 ile 255 karakter arasında olmalıdır")
    private  String detail;



}

package com.myworks.mywork.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User extends BaseEntity {
    @NotNull(message = "Cannot be null")
    @Size(min = 3, max = 50, message = "Min 3 Max 50")
    @NotEmpty(message = "Cannot be empty")
    private String username;

    @NotNull(message = "Cannot be null")
    @NotEmpty(message = "Cannot be empty")
    @Email(message = "Must be email")
    private String email;

    @NotNull(message = "Cannot be null")
    @Size(min = 3, max = 50, message = "Min 3 Max 50")
    @NotEmpty(message = "Cannot be empty")
    private String name;

    @NotNull(message = "Cannot be null")
    @Size(min = 3, max = 50, message = "Min 3 Max 50")
    @NotEmpty(message = "Cannot be empty")
    private String surname;

    private String fullName;

    public String getFullName() {
        return this.name + " " + this.surname;
    }

    public void setFullName() {
        this.fullName=this.name+ " " + this.surname;
    }


    @Column(nullable = false)
    private String password;


}

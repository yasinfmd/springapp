package com.myworks.mywork.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User extends BaseEntity {

    private String username;

    private String email;


    private String name;


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

package com.myworks.mywork.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@JsonIgnoreProperties({"files"})
public class Todo extends BaseEntity {
    @NotNull(message = "Cannot be null")
    @Size(min = 10, max = 150, message = "Min 10 Max 150")
    @NotEmpty(message = "Cannot be empty")
    private String title;
    @NotNull(message = "Cannot be null")
    @Size(min = 5, max = 150, message = "Min 5 Max 150")
    private String text;
    @NotNull(message = "Cannot be null")
    private Boolean completed;


    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "todo_detail_id", referencedColumnName = "id")
    private TodoDetail todoDetail;
    /*
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    private  User user;

     */

    @OneToMany(mappedBy = "todo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<File> files = new ArrayList<>();


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "todo_tag",
            joinColumns = @JoinColumn(name = "todo_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    @JsonManagedReference
    private List<Tag> tags=new ArrayList<>();
}

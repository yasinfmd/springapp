package com.myworks.mywork.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tag  extends  BaseEntity{
    @NotNull
    private String name;

    @ManyToMany(mappedBy = "tags",fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Todo> todos=new ArrayList<>();

}

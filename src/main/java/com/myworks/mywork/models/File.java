package com.myworks.mywork.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class File extends BaseEntity {
    private String fileName;
    private String filePath;
    private long fileSize;
    private String fileType;
    private String fileUrl;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "todo_id",nullable = false)
    private Todo todo;
}

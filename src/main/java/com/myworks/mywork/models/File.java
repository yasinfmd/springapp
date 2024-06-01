package com.myworks.mywork.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class File extends BaseEntity {
    private String fileName;
    private String filePath;
    private long fileSize;
    private String fileType;
    private String fileUrl;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_id",nullable = false)
    private Todo todo;
}

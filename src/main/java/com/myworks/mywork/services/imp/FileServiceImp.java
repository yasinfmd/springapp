package com.myworks.mywork.services.imp;


import com.myworks.mywork.models.File;
import com.myworks.mywork.models.Todo;
import com.myworks.mywork.repository.FileRepository;
import com.myworks.mywork.repository.TodoRepository;
import com.myworks.mywork.services.FileService;
import com.myworks.mywork.config.FileConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
public class FileServiceImp implements FileService {
    private final FileRepository fileRepository;
    private final Path fileStorageLocation;
    private final String baseUrl;
    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    public FileServiceImp(FileConfig fileConfig, FileRepository fileRepository,TodoRepository todoRepository) {
        this.todoRepository=todoRepository;
        this.fileRepository = fileRepository;
        this.fileStorageLocation = Paths.get(fileConfig.getUploadDir()).toAbsolutePath().normalize();
        this.baseUrl = fileConfig.getBaseUrl();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            log.error(ex.toString());
            throw new RuntimeException("Could not create the directory where the uploaded files will be stored.", ex);
        }

    }

    @Override
    @Transactional
    public Boolean store(MultipartFile file , UUID id) {
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("File is Empty");
            }
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path targetLocation = this.fileStorageLocation.resolve(fileName);

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, targetLocation,
                        StandardCopyOption.REPLACE_EXISTING);
            }
            File fileEntity = new File();
            fileEntity.setFileName(fileName);
            fileEntity.setFileSize(file.getSize());
            fileEntity.setFileType(file.getContentType());
            fileEntity.setFilePath(targetLocation.toString());
            fileEntity.setFileUrl(this.baseUrl + "uploads/" + fileName);
            Todo todo = todoRepository.findById(id).orElseThrow(() -> new RuntimeException("Todo not found"));
            fileEntity.setTodo(todo);
            todo.getFiles().add(fileEntity);
            this.fileRepository.save(fileEntity);
            return true;

        } catch (Exception e) {
            throw new RuntimeException(e.toString());
        }
    }
}

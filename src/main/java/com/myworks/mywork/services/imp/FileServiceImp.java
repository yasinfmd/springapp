package com.myworks.mywork.services.imp;

import com.myworks.mywork.services.FileService;
import com.myworks.mywork.config.FileConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
public class FileServiceImp  implements FileService {

    private final Path fileStorageLocation;

    @Autowired
    public FileServiceImp(FileConfig fileConfig){
        this.fileStorageLocation= Paths.get(fileConfig.getUploadDir()) .toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            log.error(ex.toString());
            throw new RuntimeException("Could not create the directory where the uploaded files will be stored.", ex);
        }

    }

    @Override
    public void store(MultipartFile file) {
        try {
            if(file.isEmpty()){
                throw  new RuntimeException("File is Empty");
            }
                String fileName = UUID.randomUUID() + "_"+ file.getOriginalFilename();
                Path targetLocation = this.fileStorageLocation.resolve(fileName);

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, targetLocation,
                        StandardCopyOption.REPLACE_EXISTING);
            }


        }catch (Exception e){
                    throw  new RuntimeException(e.toString());
        }
    }
}

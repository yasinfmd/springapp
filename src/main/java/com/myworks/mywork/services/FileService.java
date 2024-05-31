package com.myworks.mywork.services;

import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface FileService {
    Boolean store(MultipartFile file, UUID id);
}

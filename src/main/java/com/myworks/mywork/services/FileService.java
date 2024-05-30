package com.myworks.mywork.services;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    void store(MultipartFile file);
}

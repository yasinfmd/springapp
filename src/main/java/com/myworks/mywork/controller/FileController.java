package com.myworks.mywork.controller;

import com.myworks.mywork.annotations.ValidImage;
import com.myworks.mywork.response.BaseResponse;
import com.myworks.mywork.services.FileService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/files")
public class FileController {
    private final FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/addFile")
    public ResponseEntity<BaseResponse<Boolean>> addFile(@Valid @ValidImage @RequestPart("image") MultipartFile image, @Valid  @NotNull @RequestPart("todoId") String id) {
        return new ResponseEntity(BaseResponse.success(this.fileService.store(image,UUID.fromString(id))), HttpStatus.OK);
    }
}

package com.myworks.mywork.controller;

import com.myworks.mywork.models.Tag;
import com.myworks.mywork.response.BaseResponse;
import com.myworks.mywork.services.TagService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tags")
public class TagController {
    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("/list")
    public ResponseEntity getTags(){
        return  new ResponseEntity<>(BaseResponse.success(tagService.getAllTags()),HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity createTag(@Valid Tag tag){
        return  new ResponseEntity(BaseResponse.success(tagService.createTag(tag)), HttpStatus.OK);
    }
}

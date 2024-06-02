package com.myworks.mywork.services.imp;

import com.myworks.mywork.models.Tag;
import com.myworks.mywork.repository.TagRepository;
import com.myworks.mywork.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImp  implements TagService {

    private final TagRepository tagRepository;

    @Autowired
    public TagServiceImp(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    @Override
    public Tag createTag(Tag tag) {
        return tagRepository.save(tag);
    }
}

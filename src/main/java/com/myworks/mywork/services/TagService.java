package com.myworks.mywork.services;

import com.myworks.mywork.models.Tag;

import java.util.List;

public interface TagService {
    List<Tag> getAllTags();

    Tag createTag(Tag tag);

}

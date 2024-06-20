package com.myworks.mywork.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    TODO_CREATE("todo:create"),
    TODO_UPDATE("todo:update"),
    TODO_DELETE("todo:delete"),
    TODO_LIST("todo:list");



    @Getter
    private final String permission;
}
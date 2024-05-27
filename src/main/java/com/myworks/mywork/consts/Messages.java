package com.myworks.mywork.consts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public enum Messages {
    SUCCESS("success"),
    ERROR("error");

    private final String value;



}

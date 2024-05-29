package com.myworks.mywork.dto.response;

import java.util.UUID;

public record TodoDetailListDTO(UUID id, int version, String detail) {
}

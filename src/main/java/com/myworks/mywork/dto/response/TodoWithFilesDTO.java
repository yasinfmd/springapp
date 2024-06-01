package com.myworks.mywork.dto.response;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public record TodoWithFilesDTO(UUID id, List<Map<String,String>> files) {
}

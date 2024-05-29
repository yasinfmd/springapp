package com.myworks.mywork.dto.response;

import java.util.UUID;

public record TodoListDTO (UUID id, int version,String title, String text , Boolean completed , TodoDetailListDTO todoDetail) {
}

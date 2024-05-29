package com.myworks.mywork.dto.response;

import java.util.UUID;

public record UserListDTO(UUID id ,String userName,  String name,String surname,String fullName,String email) {
}

package com.myworks.mywork.dto.response;

public record SigninResponse(String token, String refreshToken, String username, String fullname) {
}

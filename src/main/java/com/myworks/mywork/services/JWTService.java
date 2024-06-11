package com.myworks.mywork.services;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

public interface JWTService {
    String extractUsername(String token);
    Date extractExpiration(String token);
    Boolean isTokenExpired(String token);
    Boolean validateToken(String token, UserDetails userDetails);
}

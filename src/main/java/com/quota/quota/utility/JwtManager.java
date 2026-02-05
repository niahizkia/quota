package com.quota.quota.utility;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class JwtManager {
    // The key must be at least 256 bits (32 characters) for HS256
    private final String SECRET_STRING = "this-is-a-very-long-and-secure-secret-key-for-quota-system";

    // Convert the string into a formal Key object
    private final SecretKey SIGNING_KEY = Keys.hmacShaKeyFor(SECRET_STRING.getBytes(StandardCharsets.UTF_8));

    private Date getIssueDate(){
        var now = LocalDateTime.now();
        var systemTime = now.atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(systemTime);
    }

    private Date getExpiredDate(){
        var expiredAt = LocalDateTime.now().plusMinutes(60);
        var systemTime = expiredAt.atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(systemTime);
    }


    private Date convertToDate(LocalDateTime ldt) {
        return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
    }

    //membuat token
    public String generateToken(String username, String role) {
        return Jwts.builder()
                .setSubject(username) // Standard practice is to put the unique identifier in Subject
                .claim("role", role)
                .setIssuer("http://localhost:7009")
                .setIssuedAt(convertToDate(LocalDateTime.now()))
                .setExpiration(convertToDate(LocalDateTime.now().plusMinutes(60))) // Re-enabled expiration
                .signWith(SIGNING_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    //Claims itu sama seperti payload
    //
    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SIGNING_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    public String getUsername(String token) {
        return getClaims(token).getSubject(); // If you stored it in setSubject
    }
}

package com.lps.subscriptions.security;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Component
public class JwtTokenUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long jwtExpirationInMs;

    private Key signingKey;


    // Método para extraer todos los claims
    public Claims extractAllClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(signingKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException e) {
            throw new IllegalArgumentException("Invalid JWT token", e);
        }
    }

    // Método para extraer el ID de la entidad del token
    public UUID extractEntityId(String token) {
        Claims claims = extractAllClaims(token);
        return UUID.fromString(claims.get("subscriberId", String.class));
    }

    // Método para validar el token
    public void validateToken(String token) throws IllegalArgumentException {
            extractAllClaims(token);
    }

    // Método para generar el token
    public String generateToken(UUID entityId) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("subscriberId", entityId);
        return createToken(claims, entityId.toString());
    }

    // Método privado para crear el token
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
                .signWith(signingKey, SignatureAlgorithm.HS256)
                .compact();
    }


}
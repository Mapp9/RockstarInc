package com.rockstarinc.backend.utils;

import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {
    private static final String SECRET_KEY = "DS30IDZZlIlms5gzcvMGry3pDc2WrYdBhu1Q7SnOn3k=";

   

    private final int jwtExpirationMs = 86400000;

    public String generateJwtToken(String username) {
        return  Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(getSignInKey())
                .compact();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Claims getClaimsFromToken(String token) {
        JwtParser parser = Jwts.parser()
                .verifyWith((SecretKey) getSignInKey())
                .build();
        return parser.parseSignedClaims(token).getPayload();
    }

    public String getUsernameFromToken(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            getClaimsFromToken(authToken);
            return true;
        } catch (Exception e) {
            System.err.println("Invalid JWT token " + e.getMessage());
        }
        return false;
    }
}

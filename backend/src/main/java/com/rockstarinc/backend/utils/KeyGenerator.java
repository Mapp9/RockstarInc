package com.rockstarinc.backend.utils;

import java.security.Key;
import java.util.Base64;

import io.jsonwebtoken.Jwts;

public class KeyGenerator {
    public static void main(String[] args) {
        Key key = Jwts.SIG.HS256.key().build();
    
    String base64Key = Base64.getEncoder().encodeToString(key.getEncoded());
    System.out.println("Base64 Key: " + base64Key);
    }
}

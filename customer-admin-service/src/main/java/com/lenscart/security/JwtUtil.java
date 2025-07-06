package com.lenscart.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil { // responsible for creating, extracting, validating, and verifying JWT (JSON Web Token) in a Spring Boot security 

    private static final String SECRET_KEY = Base64.getEncoder().encodeToString(
            "mySuperSecretKeyForJwtAuthenticationInSpringBoot12345".getBytes()
    );  
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 10; // 10 hours

    // ✅ **Generate JWT Token (Now includes role)**
    public String generateToken(String username, String role) { //Generates JWT Token
        return Jwts.builder()
                .setSubject(username) //stores the username inside the token
                .claim("role", role)  // Add role as claim
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256) //Signs the JWT with HMAC SHA-256 and a secret key
                .compact(); //Finalizes the JWT and converts it into a compact string.


    }

    // ✅ **Extract Username from Token**
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject); //Claims::getSubject retrieves the subject (username) from the token
    }

    // ✅ **Extract Role from Token**
    public String extractRole(String token) {
        return extractClaim(token, claims -> claims.get("role", String.class));
    }

    // ✅ **Extract Expiration Date from Token**
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // ✅ **Extract Specific Claim from Token**
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) { //This generic method extracts any claim from a JWT.
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims); //Uses Java functional programming to extract a specific claim
    }

    // ✅ **Extract All Claims**
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder() //parses
                .setSigningKey(getSigningKey()) //Uses secret key to verify the token's signature.
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // ✅ **Validate Token**
    public boolean validateToken(String token, String username) {
        return (username.equals(extractUsername(token)) && !isTokenExpired(token));
    }

    // ✅ **Check if Token is Expired**
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // ✅ **Get Signing Key**
    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}

package com.ecommerce.infrastructure.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class JwtUtil {
    private final Long ACCESS_EXPIRATION = 1000 * 60 * 15L;
    private final Long REFRESH_EXPIRATION = 1000 * 60 * 60 * 24L;
    @Value("${security.jwt.secret}")
    private String secret;
    private final RedisTemplate<String, String> redisTemplate;

    public String generateAccessToken(String username) {
        return generateToken(username, ACCESS_EXPIRATION);
    }

    public String generateRefreshToken(String username) {
        return generateToken(username, REFRESH_EXPIRATION);
    }

    private String generateToken(String username, Long expirationTime) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean validateToken(String token, String username) {
        String tokenUsername = extractUsername(token);
        return username.equals(tokenUsername) && !isTokenExpired(token) && !isTokenBlacklisted(token);
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    public void blacklistToken(String token) {
        redisTemplate.opsForValue().set(token, "blacklist", ACCESS_EXPIRATION, TimeUnit.MILLISECONDS);
    }

    public boolean isTokenBlacklisted(String token) {
        return redisTemplate.hasKey(token);
    }
}
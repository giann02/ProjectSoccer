package com.tutorial.projectSoccer.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Date;

public class TokenUtils {
    private final static String ACCESS_TOKEN_SECRET = String.valueOf(Keys.secretKeyFor(SignatureAlgorithm.HS256));
    ;
    private final static long ACCESS_TOKEN_VALIDITY_SECONDS = 2_592_000L;

    public static String createToken(String nombre) {
        long expirationTime = ACCESS_TOKEN_VALIDITY_SECONDS * 1000;
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);


        return Jwts.builder()
                .setSubject(nombre)
                .setExpiration(expirationDate)
                .signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes(StandardCharsets.UTF_8)))
                .compact();
    }


    public static UsernamePasswordAuthenticationToken getAuthentication(String token){
        try{
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            String nombre = claims.getSubject();

            return new UsernamePasswordAuthenticationToken(nombre,null, Collections.emptyList());
        }catch (JwtException e){
            return null;
        }
    }
}

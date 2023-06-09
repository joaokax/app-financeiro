package com.example.appfinanceiro.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtGenerator {

    @Value("${auth0.jwtSecret}")
    private String jwtSecret;

    @Value("${auth0.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String generateJwtToken(Authentication authentication) {
        String username = authentication.getName();
        Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
        Map<String, Object> headerClaims = new HashMap<>();
        headerClaims.put("typ", "JWT");
        headerClaims.put("alg", "HS256");

        return JWT.create()
                .withHeader(headerClaims)
                .withSubject(username)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .sign(algorithm);
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public String extractUsername(String token) {
        DecodedJWT decodedJWT = JWT.decode(token);
        return decodedJWT.getSubject();
    }

    public boolean isTokenExpired(String token) {
        DecodedJWT decodedJWT = JWT.decode(token);
        Date expirationDate = decodedJWT.getExpiresAt();
        return expirationDate.before(new Date());
    }
}

package org.example.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.common.exceptions.WrongCredentialsException;
import org.example.persistence.model.JwtEntity;
import org.example.persistence.model.UserAccountEntity;
import org.example.persistence.repository.JwtRepository;
import org.example.persistence.repository.UserAccountRepository;
import org.example.validators.JwtValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.sql.Date;
import java.util.UUID;

@Service
public class JwtService {

    private final JwtRepository jwtRepository;
    private final JwtValidation jwtValidation;
    private final UserAccountRepository userAccountRepository;
    private final Key key;

    @Autowired
    public JwtService(JwtRepository jwtRepository, JwtValidation jwtValidation, UserAccountRepository userAccountRepository, Key key) {
        this.jwtRepository = jwtRepository;
        this.jwtValidation = jwtValidation;
        this.userAccountRepository = userAccountRepository;
        this.key = key;
    }


    public String generateAccessToken(int userId, String email) {
        long now = System.currentTimeMillis();
        long ACCESS_EXPIRATION = 1000 * 60 * 15;
        return Jwts.builder()
                .setSubject("userToken")
                .claim("user", new TokenBody(userId, email))
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + ACCESS_EXPIRATION))
                .setId(UUID.randomUUID().toString())
                .signWith(key)
                .compact();
    }

    public String generateRefreshToken(String userId) {
        long now = System.currentTimeMillis();
        long REFRESH_EXPIRATION = 1000L * 60 * 60 * 24 * 30;
        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + REFRESH_EXPIRATION))
                .setId(UUID.randomUUID().toString())
                .signWith(key)
                .compact();
    }

    public void saveRefreshToken(String token) {
        JwtEntity jwtEntity = new JwtEntity();

        Claims claims = jwtValidation.getClaims(token);

        Integer userId = Integer.parseInt(claims.getSubject());

        UserAccountEntity user = userAccountRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        jwtEntity.setRefreshToken(token);
        jwtEntity.setUserAccount(user);
        jwtEntity.setJti(claims.getId());
        jwtEntity.setExp(claims.getExpiration().toString());
        jwtEntity.setIat(claims.getIssuedAt().toString());

        jwtRepository.save(jwtEntity);
    }


    public void deleteRefreshToken(Integer id) {
        jwtRepository.deleteByUserAccount_Id(id);
    }

    public String getAccessToken(String token) throws WrongCredentialsException {
        if (jwtValidation.validateToken(token)) {
            Claims claims = jwtValidation.getClaims(token);
            int userId = Integer.parseInt(claims.getSubject());
            UserAccountEntity user = userAccountRepository.findById(userId)
                    .orElseThrow(() -> new WrongCredentialsException("User not found"));
            return generateAccessToken(userId, user.getEmail());
        }
        return null;
    }

    @AllArgsConstructor
    @Getter
    public static class TokenBody {
        private int userId;
        private String email;
    }
}

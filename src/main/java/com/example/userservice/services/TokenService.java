package com.example.userservice.services;

import com.example.userservice.dtos.TokenResponseDto;
import com.example.userservice.mappers.TokenMapper;
import com.example.userservice.models.Token;
import com.example.userservice.models.User;
import com.example.userservice.repos.TokenRepo;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TokenService {
    private final TokenMapper tokenMapper;
    TokenRepo tokenRepo;

    public TokenService(TokenRepo tokenRepo, TokenMapper tokenMapper) {
        this.tokenRepo = tokenRepo;
        this.tokenMapper = tokenMapper;
    }

    public TokenResponseDto generateToken(User user) {
        Token tokenEntity = new Token();
        tokenEntity.setValue(RandomStringUtils.randomAlphanumeric(10));
        tokenEntity.setExpiryAt(System.currentTimeMillis() + 3600000);
        tokenEntity.setUser(user);
        tokenRepo.save(tokenEntity);

        // Create TokenResponseDto
        return tokenMapper.toDto(tokenEntity, user);
    }

    public User validateToken(String token) {
        Optional<Token> tokenOptional = tokenRepo.findByValueAndDeletedAndExpiryAtGreaterThan(token, false, System.currentTimeMillis());

        return tokenOptional.map(Token::getUser).orElse(null);
    }
}

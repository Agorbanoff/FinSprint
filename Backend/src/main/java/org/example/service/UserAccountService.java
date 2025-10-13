package org.example.service;

import org.example.controller.Model.TokenResponseDTO;
import org.example.controller.Model.UserAccountDTO;
import org.example.common.exceptions.EmailAlreadyInUseException;
import org.example.common.exceptions.WrongCredentialsException;
import org.example.persistence.model.UserAccountEntity;
import org.example.persistence.repository.JwtRepository;
import org.example.persistence.repository.UserAccountRepository;
import org.example.validators.JwtValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtValidation jwtValidation;
    private final JwtRepository jwtRepository;

    @Autowired
    public UserAccountService(UserAccountRepository userAccountRepository,
                              JwtService jwtService,
                              BCryptPasswordEncoder passwordEncoder,
                              JwtValidation jwtValidation,
                              JwtRepository jwtRepository) {
        this.userAccountRepository = userAccountRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.jwtValidation = jwtValidation;
        this.jwtRepository = jwtRepository;
    }

    public TokenResponseDTO signUp(UserAccountDTO userAccountDTO) throws EmailAlreadyInUseException {
        if (userAccountRepository.existsByEmail(userAccountDTO.getEmail())) {
            throw new EmailAlreadyInUseException("Email is already in use!");
        }

        UserAccountEntity userAccountEntity = new UserAccountEntity();
        userAccountEntity.setUsername(userAccountDTO.getUsername());
        userAccountEntity.setEmail(userAccountDTO.getEmail());
        userAccountEntity.setPassword(passwordEncoder.encode(userAccountDTO.getPassword()));

        UserAccountEntity savedUser = userAccountRepository.save(userAccountEntity);

        int userId = (savedUser.getId());
        String accessToken = jwtService.generateAccessToken(userId, userAccountDTO.getEmail());
        String refreshToken = jwtService.generateRefreshToken(String.valueOf(userId));

        jwtService.saveRefreshToken(refreshToken);

        TokenResponseDTO tokenResponseDTO = new TokenResponseDTO();
        tokenResponseDTO.setAccessToken(accessToken);
        tokenResponseDTO.setRefreshToken(refreshToken);

        return tokenResponseDTO;
    }

    public TokenResponseDTO logIn(UserAccountDTO userAccountDTO) throws WrongCredentialsException {
        UserAccountEntity user = userAccountRepository.findByEmail(userAccountDTO.getEmail());

        if (user == null || !passwordEncoder.matches(userAccountDTO.getPassword(), user.getPassword())) {
            throw new WrongCredentialsException("Wrong credentials!");
        }

        int userId = user.getId();

        String accessToken = jwtService.generateAccessToken(userId, userAccountDTO.getEmail());
        String refreshToken = jwtService.generateRefreshToken(String.valueOf(userId));

        jwtService.saveRefreshToken(refreshToken);

        TokenResponseDTO tokenResponseDTO = new TokenResponseDTO();
        tokenResponseDTO.setAccessToken(accessToken);
        tokenResponseDTO.setRefreshToken(refreshToken);

        return tokenResponseDTO;
    }

    public void logOut(String refreshToken) {
        String userId = jwtValidation.getClaims(refreshToken).getSubject();
        jwtRepository.deleteRefreshTokenById(Integer.valueOf(userId));
    }
}
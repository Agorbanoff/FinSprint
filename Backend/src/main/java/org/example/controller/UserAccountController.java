package org.example.controller;

import org.example.common.exceptions.UserNotFoundException;
import org.example.common.exceptions.WrongTokenException;
import org.example.controller.Model.TokenResponseDTO;
import org.example.controller.Model.UserAccountDTO;
import org.example.common.exceptions.EmailAlreadyInUseException;
import org.example.common.exceptions.WrongCredentialsException;
import org.example.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class UserAccountController {
    private final UserAccountService userAccountService;

    @Autowired
    public UserAccountController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @PostMapping(value = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TokenResponseDTO> signUp(@RequestBody UserAccountDTO userAccountDTO) throws EmailAlreadyInUseException {
        TokenResponseDTO response = userAccountService.signUp(userAccountDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TokenResponseDTO> logIn(@RequestBody UserAccountDTO userAccountDTO) throws UserNotFoundException, WrongCredentialsException {
        TokenResponseDTO response = userAccountService.logIn(userAccountDTO);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @PostMapping(value = "/logout")
    public ResponseEntity<Void> logOut(@RequestHeader("Authorization") String refreshToken) {
        userAccountService.logOut(refreshToken);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}
package org.example.controller;
import org.example.common.exceptions.WrongCredentialsException;
import org.example.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
public class TokenController {

    private final JwtService jwtService;

    @Autowired
    public TokenController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

@PostMapping()
    public ResponseEntity<String> getAccessToken(@RequestHeader("Authorization") String refreshToken) throws WrongCredentialsException {
        String response = jwtService.getAccessToken(refreshToken);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}

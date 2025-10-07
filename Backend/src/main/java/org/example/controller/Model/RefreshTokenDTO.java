package org.example.controller.Model;

import lombok.Data;

@Data
public class RefreshTokenDTO {

    private String refreshToken;
    private String iat;
    private String exp;
    private String jti;
}

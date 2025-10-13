package org.example.controller.Model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RefreshTokenDTO {

    @NotBlank(message = "refresh token required")
    private String refreshToken;
    @NotBlank(message = "iat required")
    private String iat;
    @NotBlank(message = "exp required")
    private String exp;
    @NotBlank(message = "jti required")
    private String jti;
}

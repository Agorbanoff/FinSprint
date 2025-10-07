package org.example.controller.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TokenResponseDTO {
    private String type = "Bearer";
    private String accessToken;
    private String refreshToken;
}

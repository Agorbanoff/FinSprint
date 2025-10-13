package org.example.controller.Model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TokenResponseDTO {
    @NotBlank
    private String type = "Bearer";
    @NotBlank
    private String accessToken;
    @NotBlank
    private String refreshToken;
}

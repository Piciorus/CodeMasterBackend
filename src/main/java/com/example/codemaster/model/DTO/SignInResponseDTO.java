package com.example.codemaster.model.DTO;

import lombok.Data;

@Data
public class SignInResponseDTO {
    private String accessToken;
    private String type;

    public SignInResponseDTO(String accessToken) {
        this.accessToken = accessToken;
        this.type = "Bearer";
    }

}

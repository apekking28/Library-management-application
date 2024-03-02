package com.ilham.libraryapp.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
public class JwtResponse implements Serializable {
    private String token;
    private String type = "Bearer";
    private String username;
    private String email;

    public JwtResponse(
            String accessToken,
            String username,
            String email) {
        this.username = username;
        this.email = email;
        this.token = accessToken;
    }


}
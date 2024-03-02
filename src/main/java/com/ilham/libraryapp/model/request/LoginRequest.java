package com.ilham.libraryapp.model.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginRequest implements Serializable {
    private String username;
    private String email;
    private String password;
}


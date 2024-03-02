package com.ilham.libraryapp.model.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class SignUpRequest implements Serializable {
    private String username;
    private String name;
    private String email;
    private String password;
}

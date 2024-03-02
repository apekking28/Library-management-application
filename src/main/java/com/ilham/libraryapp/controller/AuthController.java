package com.ilham.libraryapp.controller;

import com.ilham.libraryapp.entity.User;
import com.ilham.libraryapp.exception.BadRequestException;
import com.ilham.libraryapp.model.request.LoginRequest;
import com.ilham.libraryapp.model.request.SignUpRequest;
import com.ilham.libraryapp.model.response.JwtResponse;
import com.ilham.libraryapp.security.jwt.JwtUtils;
import com.ilham.libraryapp.security.service.UserDetailsImpl;
import com.ilham.libraryapp.service.AuthService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
@Api(value = "Authentication API with JWT", tags = "Authentication")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    AuthService authService;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signing")
    public ResponseEntity<JwtResponse> authenticateUser(@RequestBody LoginRequest request) {
        JwtResponse jwtResponse = null;
        try {
            jwtResponse = authService.signIng(request);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new BadRequestException(ex.getMessage());
        }
        return ResponseEntity.ok().body(new JwtResponse(jwtResponse.getToken(), jwtResponse.getUsername(), jwtResponse.getEmail()));
    }

    @PostMapping("/signup")
    public User signup(@RequestBody SignUpRequest request) {
        try {
            return authService.signUp(request);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new BadRequestException(ex.getMessage());
        }
    }
}

package com.ilham.libraryapp.service;

import com.ilham.libraryapp.entity.User;
import com.ilham.libraryapp.exception.BadRequestException;
import com.ilham.libraryapp.exception.ResourceNotFoundException;
import com.ilham.libraryapp.model.request.LoginRequest;
import com.ilham.libraryapp.model.request.SignUpRequest;
import com.ilham.libraryapp.model.response.JwtResponse;
import com.ilham.libraryapp.repository.UserRepository;
import com.ilham.libraryapp.security.jwt.JwtUtils;
import com.ilham.libraryapp.security.service.UserDetailsImpl;
import com.ilham.libraryapp.util.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
@Service
public class AuthService {

    @Autowired
    UserRepository userRepository;


    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    public User signUp(SignUpRequest request) {
        if (!StringUtils.hasText(request.getUsername())) {
            throw new BadRequestException("Username required");
        }

        if (userRepository.existsById(request.getUsername())) {
            throw new BadRequestException("Username " + request.getUsername() + " already exsit");
        }
        if (!StringUtils.hasText(request.getEmail())) {
            throw new BadRequestException("Email required");
        }

        if(!Validation.isValidEmail(request.getEmail())) {
            throw new BadRequestException("Invalid email please use like format: @gmail.com, @hotmail.com");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Email " + request.getEmail() + " already exist");
        }

        if(!Validation.isValidPassword(request.getPassword())) {
            throw new BadRequestException("Passwords must consist of 8 alphanumeric characters and must contain at least 1 capital letter capital letter, cannot contain special characters");
        }

        User user = new User();
        user.setId(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setName(request.getName());
        user.setRoles("user");
        user.setIsActive(true);
        return userRepository.save(user);
    }

    public JwtResponse signIng(LoginRequest request) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        SecurityContextHolder.getContext()
                .setAuthentication(authentication);
        String token = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl principal = (UserDetailsImpl) authentication.getPrincipal();
        return new JwtResponse(token, principal.getUsername(), principal.getEmail());
    }

    public List<User> listUser() {
        return userRepository.findAll();
    }
}

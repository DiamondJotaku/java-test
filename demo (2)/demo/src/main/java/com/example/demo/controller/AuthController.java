package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.JwtResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.UserService;

import lombok.RequiredArgsConstructor;

@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody LoginRequest req) {
    authManager.authenticate(
        new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));

    String token = jwtUtil.generateToken(req.getUsername());
    User user = userRepository.findByUsername(req.getUsername()).get();

    return ResponseEntity.ok(new JwtResponse(token, user.getUsername(), user.getRole())); // ← getRole()
}
package com.example.demo.dto;

import java.util.Set;

import com.example.demo.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponse {
    private String token;
    private String username;
    private Role role;
}
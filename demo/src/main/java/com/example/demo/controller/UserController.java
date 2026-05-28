package com.example.demo.controller;

import com.example.demo.dto.UpdateProfileRequest;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    // Voir son propre profil
    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(Authentication auth) {
        User user = userRepository.findByUsername(auth.getName())
            .orElseThrow(() -> new RuntimeException("User not found"));
        return ResponseEntity.ok(user);
    }

    // Modifier son propre profil
    @PutMapping("/profile")
    public ResponseEntity<?> updateProfile(Authentication auth,
                                           @RequestBody UpdateProfileRequest req) {
        User user = userRepository.findByUsername(auth.getName())
            .orElseThrow(() -> new RuntimeException("User not found"));
        user.setEmail(req.getEmail());
        userRepository.save(user);
        return ResponseEntity.ok(user);
    }
}

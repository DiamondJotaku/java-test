package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public ResponseEntity<String> home() {
        // Retourne une page HTML avec une image
        String html = """
            <html>
                <body>
                    <h1>Bienvenue sur l'API</h1>
                </body>
            </html>
            """;
        return ResponseEntity.ok()
            .header("Content-Type", "text/html")
            .body(html);
    }
}

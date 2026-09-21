package com.brilloestelar.auth.controller;

import com.brilloestelar.auth.model.Usuario;
import com.brilloestelar.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Usuario usuario) {
        try {
            String result = authService.registerUser(usuario);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody Usuario loginRequest) {
        try {
            String jwt = authService.authenticateUser(loginRequest.getUsername(), loginRequest.getPassword());
            Map<String, String> response = new HashMap<>();
            response.put("token", jwt);
            response.put("type", "Bearer");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}

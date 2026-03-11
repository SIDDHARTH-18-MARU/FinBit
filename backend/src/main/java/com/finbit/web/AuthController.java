package com.finbit.web;

import com.finbit.domain.User;
import com.finbit.repo.UserRepository;
import com.finbit.web.dto.LoginRequest;
import com.finbit.web.dto.SignupRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserRepository users;

    public AuthController(UserRepository users) {
        this.users = users;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody @Valid SignupRequest req) {
        if (users.findByEmail(req.email()).isPresent()) {
            return ResponseEntity.badRequest().body("Email already registered");
        }
        User u = User.builder()
                .fullName(req.fullName())
                .email(req.email())
                .phone(req.phone())
                .password(req.password()) // NOTE: plaintext for demo only
                .build();
        users.save(u);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest req) {
        return users.findByEmail(req.email())
                .filter(u -> u.getPassword().equals(req.password()))
                .<ResponseEntity<?>>map(u -> ResponseEntity.ok().build())
                .orElseGet(() -> ResponseEntity.status(401).body("Invalid credentials"));
    }
}

package com.example.demo.Controller;

import com.example.demo.DTO.AuthRequest;
import com.example.demo.DTO.AuthResponse;
import com.example.demo.DTO.SignupRequest;
import com.example.demo.Service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public AuthResponse signup(@RequestBody SignupRequest req) {
        return authService.signup(req);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest req) {
        return authService.login(req);
    }
}

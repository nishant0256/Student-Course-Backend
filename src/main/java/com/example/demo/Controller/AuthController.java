package com.example.demo.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.DTO.*;
import com.example.demo.Entity.*;
import com.example.demo.Repositories.*;
import com.example.demo.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
    @Autowired private UserRepository userRepo;
    @Autowired private RoleRepository roleRepo;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private JwtUtil jwtUtil;

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest req) {
        if (userRepo.existsByUsername(req.getUsername())) {
            throw new RuntimeException("Username exists");
        }
        User u = new User();
        u.setUsername(req.getUsername());
        u.setPassword(passwordEncoder.encode(req.getPassword()));
        String roleName = req.getRole() == null ? "ROLE_STUDENT" : req.getRole();
        Role r = roleRepo.findByName(roleName).orElseGet(() -> roleRepo.save(new Role(roleName)));
        u.getRoles().add(r);
        userRepo.save(u);

        String token = jwtUtil.generateToken(u.getUsername(), u.getRoles().stream().map(Role::getName).collect(Collectors.toList()));
        return new AuthResponse(token, u.getUsername(), roleName);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest req) {
        User u = userRepo.findByUsername(req.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));
        if (!passwordEncoder.matches(req.getPassword(), u.getPassword())) {
            throw new RuntimeException("Bad credentials");
        }
        String token = jwtUtil.generateToken(u.getUsername(), u.getRoles().stream().map(Role::getName).collect(Collectors.toList()));
        String role = u.getRoles().stream().findFirst().map(Role::getName).orElse("ROLE_STUDENT");
        return new AuthResponse(token, u.getUsername(), role);
    }
}

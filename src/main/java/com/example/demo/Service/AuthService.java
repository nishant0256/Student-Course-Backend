package com.example.demo.Service;

import com.example.demo.DTO.AuthRequest;
import com.example.demo.DTO.AuthResponse;
import com.example.demo.DTO.SignupRequest;
import com.example.demo.Entity.Role;
import com.example.demo.Entity.User;
import com.example.demo.Repositories.RoleRepository;
import com.example.demo.Repositories.UserRepository;
import com.example.demo.security.JwtUtil;

import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.stream.Collectors;

@Service
public class AuthService {

    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(
            UserRepository userRepo,
            RoleRepository roleRepo,
            PasswordEncoder passwordEncoder,
            JwtUtil jwtUtil
    ) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    // ============================
    // SIGNUP
    // ============================
    public AuthResponse signup(SignupRequest req) {

        if (userRepo.findByUsername(req.getEmail()).isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "User already exists"
            );
        }

        User user = new User();
        user.setUsername(req.getEmail());
        user.setPassword(passwordEncoder.encode(req.getPassword()));

        // 🔥 CRITICAL FIX
        user.setEnabled(true);

        Role role = roleRepo.findByName("ROLE_STUDENT")
                .orElseGet(() -> roleRepo.save(new Role("ROLE_STUDENT")));

        user.getRoles().add(role);
        userRepo.save(user);

        String token = jwtUtil.generateToken(
                user.getUsername(),
                user.getRoles()
                        .stream()
                        .map(Role::getName)
                        .collect(Collectors.toList())
        );

        return new AuthResponse(token, user.getUsername(), role.getName());
    }

    // ============================
    // LOGIN
    // ============================
    public AuthResponse login(AuthRequest req) {

        User user = userRepo.findByUsername(req.getUsername())
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND, "User not found"
                        )
                );

        // 🔥 CRITICAL FIX
        if (!user.isEnabled()) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN, "User account is disabled"
            );
        }

        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "Invalid credentials"
            );
        }

        String role = user.getRoles()
                .stream()
                .findFirst()
                .map(Role::getName)
                .orElse("ROLE_STUDENT");

        String token = jwtUtil.generateToken(
                user.getUsername(),
                user.getRoles()
                        .stream()
                        .map(Role::getName)
                        .collect(Collectors.toList())
        );

        return new AuthResponse(token, user.getUsername(), role);
    }
}

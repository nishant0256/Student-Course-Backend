package com.example.demo.DTO;

public class SignupRequest {
    private String name;     // optional
    private String email;
    private String password;

    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
}

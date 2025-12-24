package com.example.tricolv2sb.Service;

import com.example.tricolv2sb.DTO.AssignRoleRequest;
import com.example.tricolv2sb.DTO.AuthResponse;
import com.example.tricolv2sb.DTO.LoginRequest;
import com.example.tricolv2sb.DTO.RegisterRequest;
import com.example.tricolv2sb.Entity.Role;
import com.example.tricolv2sb.Entity.User;
import com.example.tricolv2sb.Repository.UserRepository;
import com.example.tricolv2sb.Security.JwtService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostConstruct
    public void createDefaultAdmin() {
        if (!userRepository.existsByUsername("admin")) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setEmail("admin@tricol.com");
            admin.setFirstName("Admin");
            admin.setLastName("User");
            admin.setRole(Role.ADMIN);
            userRepository.save(admin);
        }
    }

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setRole(null);
        
        userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        
        return new AuthResponse("User registered successfully", user.getUsername(), "No role assigned", jwtToken);
    }

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        String jwtToken = jwtService.generateToken(user);
        String roleName = user.getRole() != null ? user.getRole().name() : "No role assigned";
        return new AuthResponse("Login successful", user.getUsername(), roleName, jwtToken);
    }

    public AuthResponse assignRole(AssignRoleRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        user.setRole(request.getRole());
        userRepository.save(user);
        
        return new AuthResponse("Role assigned successfully", user.getUsername(), request.getRole().name(), null);
    }
}

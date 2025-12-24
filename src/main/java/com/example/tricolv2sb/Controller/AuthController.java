package com.example.tricolv2sb.Controller;

import com.example.tricolv2sb.DTO.AssignRoleRequest;
import com.example.tricolv2sb.DTO.AuthResponse;
import com.example.tricolv2sb.DTO.LoginRequest;
import com.example.tricolv2sb.DTO.RegisterRequest;
import com.example.tricolv2sb.Entity.Permission;
import com.example.tricolv2sb.Security.RequirePermission;
import com.example.tricolv2sb.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/assign-role")
    @RequirePermission(Permission.USER_MANAGEMENT)
    public ResponseEntity<AuthResponse> assignRole(@RequestBody AssignRoleRequest request) {
        return ResponseEntity.ok(authService.assignRole(request));
    }
}

package com.example.tricolv2sb.DTO;

import com.example.tricolv2sb.Entity.Role;
import lombok.Data;

@Data
public class AssignRoleRequest {
    private String username;
    private Role role;
}
package com.example.tricolv2sb.DTO;

import com.example.tricolv2sb.Entity.Permission;
import lombok.Data;

@Data
public class UserPermissionRequest {
    private Long userId;
    private Permission permission;
}
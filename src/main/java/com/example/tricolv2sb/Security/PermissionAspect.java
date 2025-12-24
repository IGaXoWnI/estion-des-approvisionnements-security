package com.example.tricolv2sb.Security;

import com.example.tricolv2sb.Service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Aspect
@Component
@RequiredArgsConstructor
public class PermissionAspect {
    
    private final PermissionService permissionService;
    
    @Before("@annotation(requirePermission)")
    public void checkPermission(RequirePermission requirePermission) {
        if (!permissionService.hasPermission(requirePermission.value())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, 
                "Accès refusé: " + requirePermission.value().getDisplayName());
        }
    }
}
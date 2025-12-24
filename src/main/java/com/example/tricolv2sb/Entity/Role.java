package com.example.tricolv2sb.Entity;

import java.util.Set;
import java.util.EnumSet;

public enum Role {
    ADMIN(EnumSet.of(
        Permission.SUPPLIER_CREATE, Permission.SUPPLIER_READ,
        Permission.PRODUCT_CREATE, Permission.PRODUCT_READ, Permission.PRODUCT_CONFIGURE_ALERTS,
        Permission.ORDER_CREATE, Permission.ORDER_VALIDATE, Permission.ORDER_CANCEL, Permission.ORDER_READ,
        Permission.STOCK_READ, Permission.STOCK_VIEW_FIFO, Permission.STOCK_HISTORY,
        Permission.EXIT_SLIP_CREATE, Permission.EXIT_SLIP_VALIDATE, Permission.EXIT_SLIP_CANCEL, Permission.EXIT_SLIP_READ,
        Permission.USER_MANAGEMENT, Permission.AUDIT_LOGS
    )),
    
    RESPONSABLE_ACHATS(EnumSet.of(
        Permission.SUPPLIER_CREATE, Permission.SUPPLIER_READ,
        Permission.PRODUCT_CREATE, Permission.PRODUCT_READ, Permission.PRODUCT_CONFIGURE_ALERTS,
        Permission.ORDER_CREATE, Permission.ORDER_VALIDATE, Permission.ORDER_CANCEL, Permission.ORDER_READ,
        Permission.STOCK_READ, Permission.STOCK_VIEW_FIFO, Permission.STOCK_HISTORY,
        Permission.EXIT_SLIP_READ
    )),
    
    MAGASINIER(EnumSet.of(
        Permission.SUPPLIER_READ,
        Permission.PRODUCT_READ,
        Permission.ORDER_RECEIVE, Permission.ORDER_READ,
        Permission.STOCK_READ, Permission.STOCK_VIEW_FIFO, Permission.STOCK_HISTORY,
        Permission.EXIT_SLIP_CREATE, Permission.EXIT_SLIP_VALIDATE, Permission.EXIT_SLIP_CANCEL, Permission.EXIT_SLIP_READ
    )),
    
    CHEF_ATELIER(EnumSet.of(
        Permission.PRODUCT_READ,
        Permission.STOCK_READ, Permission.STOCK_HISTORY,
        Permission.EXIT_SLIP_CREATE, Permission.EXIT_SLIP_READ
    ));
    
    private final Set<Permission> permissions;
    
    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }
    
    public Set<Permission> getPermissions() {
        return permissions;
    }
    
    public boolean hasPermission(Permission permission) {
        return permissions.contains(permission);
    }
}
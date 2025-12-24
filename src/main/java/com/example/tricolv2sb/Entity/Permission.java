package com.example.tricolv2sb.Entity;

public enum Permission {
    // Fournisseurs
    SUPPLIER_CREATE("Créer/Modifier des fournisseurs"),
    SUPPLIER_READ("Consulter les fournisseurs"),
    
    // Produits
    PRODUCT_CREATE("Créer/Modifier des produits"),
    PRODUCT_READ("Consulter les produits"),
    PRODUCT_CONFIGURE_ALERTS("Configurer les seuils d'alerte"),
    
    // Commandes Fournisseurs
    ORDER_CREATE("Créer/Modifier des commandes"),
    ORDER_VALIDATE("Valider les commandes"),
    ORDER_CANCEL("Annuler les commandes"),
    ORDER_RECEIVE("Réceptionner les commandes"),
    ORDER_READ("Consulter les commandes"),
    
    // Stock & Lots
    STOCK_READ("Consulter le stock"),
    STOCK_VIEW_FIFO("Voir la valorisation FIFO"),
    STOCK_HISTORY("Consulter l'historique des mouvements"),
    
    // Bons de Sortie
    EXIT_SLIP_CREATE("Créer des bons de sortie"),
    EXIT_SLIP_VALIDATE("Valider les bons de sortie"),
    EXIT_SLIP_CANCEL("Annuler les bons de sortie"),
    EXIT_SLIP_READ("Consulter les bons de sortie"),
    
    // Administration
    USER_MANAGEMENT("Gérer les utilisateurs"),
    AUDIT_LOGS("Consulter les logs d'audit");
    
    private final String displayName;
    
    Permission(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
}
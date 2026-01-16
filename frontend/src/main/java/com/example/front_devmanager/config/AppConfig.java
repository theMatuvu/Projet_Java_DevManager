package com.example.front_devmanager.config;

/**
 * Configuration de l'application
 * Modifiez ces constantes selon votre environnement
 */
public class AppConfig {
    
    /**
     * URL de base de l'API backend
     * Par défaut: http://localhost:8080/api
     */
    public static final String API_BASE_URL = "http://localhost:8080/api";
    
    /**
     * Timeout pour les requêtes HTTP (en secondes)
     */
    public static final int HTTP_TIMEOUT = 30;
    
    /**
     * Titre de l'application
     */
    public static final String APP_TITLE = "DevManager Pro";
    
    /**
     * Version de l'application
     */
    public static final String APP_VERSION = "1.0.0";
    
    /**
     * Largeur par défaut de la fenêtre
     */
    public static final int DEFAULT_WINDOW_WIDTH = 1400;
    
    /**
     * Hauteur par défaut de la fenêtre
     */
    public static final int DEFAULT_WINDOW_HEIGHT = 900;
    
    /**
     * Activer le mode debug (affichage des logs détaillés)
     */
    public static final boolean DEBUG_MODE = true;
}

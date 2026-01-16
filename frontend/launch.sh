#!/bin/bash

echo "========================================"
echo "  DevManager Pro - Lancement"
echo "========================================"
echo ""

echo "[1/3] Vérification de Java..."
java -version
if [ $? -ne 0 ]; then
    echo "ERREUR: Java n'est pas installé ou n'est pas dans le PATH"
    exit 1
fi
echo ""

echo "[2/3] Vérification de Maven..."
mvn -version
if [ $? -ne 0 ]; then
    echo "ERREUR: Maven n'est pas installé ou n'est pas dans le PATH"
    exit 1
fi
echo ""

echo "[3/3] Compilation et lancement de l'application..."
echo ""
mvn clean javafx:run

if [ $? -ne 0 ]; then
    echo ""
    echo "ERREUR: Échec du lancement de l'application"
    echo "Vérifiez que le backend est démarré sur http://localhost:8080"
    exit 1
fi

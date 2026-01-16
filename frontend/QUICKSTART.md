# 🚀 Guide de Démarrage Rapide - DevManager Pro

## Étape 1 : Démarrer le Backend

1. Ouvrez un terminal et naviguez vers le projet backend :
```bash
cd Projet_Java_DevManager
```

2. Démarrez le serveur Spring Boot :
```bash
mvn spring-boot:run
```

3. Vérifiez que le serveur est démarré sur `http://localhost:8080`

## Étape 2 : Lancer l'Application Frontend

1. Ouvrez un **nouveau terminal** et naviguez vers le projet frontend :
```bash
cd Front_DevManager
```

2. Compilez et lancez l'application JavaFX :
```bash
mvn clean javafx:run
```

## Étape 3 : Utiliser l'Application

L'application DevManager Pro s'ouvre avec :

### 🏠 Dashboard
- Vue d'ensemble avec statistiques
- Cartes animées pour les programmeurs, projets et projets en cours

### 👥 Gestion des Programmeurs
- Cliquez sur "Programmeurs" dans la sidebar
- **Ajouter** : Bouton "Nouveau Programmeur"
- **Modifier le salaire** : Icône 💰 sur chaque ligne
- **Supprimer** : Icône 🗑️ avec confirmation
- **Rechercher** : Utilisez le champ de recherche en haut à droite

### 📊 Gestion des Projets
- Cliquez sur "Projets" dans la sidebar
- **Ajouter** : Bouton "Nouveau Projet"
- **Modifier** : Icône ✏️ sur chaque ligne
- **Supprimer** : Icône 🗑️ avec confirmation
- **Rechercher** : Utilisez le champ de recherche en haut à droite

## 🎨 Fonctionnalités Visuelles

- **Animations fluides** lors des changements de vue
- **Hover effects** sur tous les boutons et cartes
- **Gradients modernes** Purple-Blue et Pink-Orange
- **Tables interactives** avec tri et redimensionnement
- **Feedback visuel** pour toutes les actions

## ⚠️ Dépannage

### L'application ne démarre pas
- Vérifiez que Java 17+ est installé : `java -version`
- Vérifiez que Maven est installé : `mvn -version`
- Exécutez `mvn clean install` avant `mvn javafx:run`

### Erreur de connexion à l'API
- Vérifiez que le backend est démarré sur le port 8080
- Testez l'API : `curl http://localhost:8080/api/programmeurs`
- Vérifiez les logs du backend pour les erreurs

### Erreurs de module JavaFX
- Assurez-vous que le JDK 17+ est utilisé
- Vérifiez que toutes les dépendances sont téléchargées : `mvn dependency:resolve`

## 📝 Raccourcis Utiles

### Backend
```bash
# Compiler
mvn clean install

# Lancer
mvn spring-boot:run

# Vérifier la santé
curl http://localhost:8080/api/programmeurs
```

### Frontend
```bash
# Compiler
mvn clean compile

# Lancer
mvn javafx:run

# Nettoyer et relancer
mvn clean javafx:run
```

## 🎯 Prochaines Étapes

1. Créez quelques programmeurs
2. Créez des projets
3. Explorez les différentes vues
4. Testez les animations et interactions
5. Utilisez la recherche pour filtrer les données

---

**Bon développement avec DevManager Pro ! 🎉**

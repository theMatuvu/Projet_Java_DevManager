# 🔧 Guide de Dépannage - DevManager Pro

## Problèmes Courants et Solutions

### 🚫 L'application ne démarre pas

#### Erreur: "Error: JavaFX runtime components are missing"

**Cause**: JavaFX n'est pas inclus dans le JDK

**Solution**:
```bash
# Vérifier la version de Java
java -version

# Utiliser Java 17+ et Maven pour lancer
mvn javafx:run
```

**Alternative**: Installer un JDK qui inclut JavaFX ou utiliser le plugin Maven JavaFX.

---

#### Erreur: "Module not found"

**Cause**: Problème avec le système de modules Java

**Solution**:
1. Vérifier `module-info.java`:
```java
requires javafx.controls;
requires javafx.fxml;
requires com.google.gson;
// etc.
```

2. Nettoyer et recompiler:
```bash
mvn clean install
mvn javafx:run
```

---

#### Erreur de compilation Maven

**Cause**: Dépendances non téléchargées

**Solution**:
```bash
# Forcer le téléchargement des dépendances
mvn dependency:resolve

# Nettoyer et réinstaller
mvn clean install -U

# Lancer
mvn javafx:run
```

---

### 🌐 Problèmes de Connexion API

#### Erreur: "Connection refused"

**Cause**: Le backend n'est pas démarré

**Solution**:
1. Vérifier que le backend est lancé:
```bash
cd Projet_Java_DevManager
mvn spring-boot:run
```

2. Vérifier l'URL dans `AppConfig.java`:
```java
public static final String API_BASE_URL = "http://localhost:8080/api";
```

3. Tester l'API manuellement:
```bash
curl http://localhost:8080/api/programmeurs
```

---

#### Erreur: "404 Not Found"

**Cause**: Endpoint incorrect ou backend non configuré

**Solution**:
1. Vérifier les endpoints dans le backend
2. Consulter `API.md` pour les endpoints corrects
3. Vérifier les logs du backend pour erreurs

---

#### Erreur: "Timeout"

**Cause**: Requête trop longue ou backend non réactif

**Solution**:
1. Augmenter le timeout dans `AppConfig.java`:
```java
public static final int HTTP_TIMEOUT = 60; // secondes
```

2. Vérifier les performances du backend
3. Vérifier la connexion réseau

---

### 🎨 Problèmes d'Affichage

#### CSS ne se charge pas

**Cause**: Chemin incorrect ou fichier manquant

**Solution**:
1. Vérifier le chemin dans `MainApplication.java`:
```java
scene.getStylesheets().add(
    getClass().getResource("/com/example/front_devmanager/styles.css")
        .toExternalForm()
);
```

2. Vérifier que `styles.css` existe dans:
```
src/main/resources/com/example/front_devmanager/styles.css
```

---

#### Icônes ne s'affichent pas

**Cause**: Dépendances Ikonli manquantes

**Solution**:
1. Vérifier `pom.xml`:
```xml
<dependency>
    <groupId>org.kordamp.ikonli</groupId>
    <artifactId>ikonli-fontawesome5-pack</artifactId>
    <version>12.3.1</version>
</dependency>
```

2. Vérifier `module-info.java`:
```java
requires org.kordamp.ikonli.javafx;
requires org.kordamp.ikonli.fontawesome5;
```

3. Recompiler:
```bash
mvn clean compile
```

---

#### Fenêtre trop petite ou trop grande

**Cause**: Dimensions par défaut inadaptées

**Solution**:
Modifier dans `AppConfig.java`:
```java
public static final int DEFAULT_WINDOW_WIDTH = 1600;  // Votre largeur
public static final int DEFAULT_WINDOW_HEIGHT = 1000; // Votre hauteur
```

---

### 📊 Problèmes de Tableaux

#### Données ne s'affichent pas

**Cause**: Propriétés non mappées correctement

**Solution**:
Vérifier les PropertyValueFactory:
```java
nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
// Le nom doit correspondre exactement au getter: getNom()
```

---

#### Erreur lors du tri

**Cause**: Données null ou type incompatible

**Solution**:
1. Vérifier que les données ne sont pas null
2. Implémenter Comparable si nécessaire
3. Gérer les null dans les comparateurs

---

### 🔄 Problèmes de CRUD

#### Création échoue silencieusement

**Cause**: Exception non gérée ou validation côté backend

**Solution**:
1. Vérifier les logs de l'application:
```java
public static final boolean DEBUG_MODE = true; // dans AppConfig
```

2. Vérifier la console pour les stack traces
3. Tester l'endpoint avec curl:
```bash
curl -X POST http://localhost:8080/api/programmeurs \
  -H "Content-Type: application/json" \
  -d '{"id":999,"nom":"Test",...}'
```

---

#### Modification ne fonctionne pas

**Cause**: ID manquant ou endpoint incorrect

**Solution**:
1. Vérifier que l'ID est bien défini
2. Pour les projets, vérifier que `projet.setId(id)` est appelé
3. Vérifier l'endpoint PUT dans le backend

---

#### Suppression ne rafraîchit pas

**Cause**: `loadProgrammeurs()` ou `loadProjets()` non appelé

**Solution**:
Vérifier que la méthode de rechargement est appelée:
```java
apiService.deleteProgrammeur(id);
loadProgrammeurs(); // ← Important !
```

---

### 🎭 Problèmes d'Animations

#### Animations saccadées

**Cause**: Trop de calculs sur le thread JavaFX

**Solution**:
1. Réduire la durée des animations
2. Utiliser `Platform.runLater()` pour les opérations lourdes
3. Optimiser les CSS transitions

---

#### Animations ne se déclenchent pas

**Cause**: Event handler mal configuré

**Solution**:
Vérifier les handlers:
```java
button.setOnMouseEntered(e -> {
    // Animation
});
```

---

### 💾 Problèmes de Sérialisation JSON

#### Erreur: "Expected BEGIN_OBJECT but was STRING"

**Cause**: Format JSON incorrect

**Solution**:
1. Vérifier le format retourné par l'API
2. Utiliser un outil comme Postman pour inspecter
3. Vérifier les adaptateurs Gson pour LocalDate

---

#### Dates ne se désérialisent pas

**Cause**: Format de date incompatible

**Solution**:
Vérifier l'adaptateur dans `ApiService.java`:
```java
.registerTypeAdapter(LocalDate.class, 
    (JsonDeserializer<LocalDate>) (json, type, context) -> 
        LocalDate.parse(json.getAsString()))
```

---

### 🐛 Debugging

#### Activer les logs détaillés

1. Dans `AppConfig.java`:
```java
public static final boolean DEBUG_MODE = true;
```

2. Ajouter des logs:
```java
if (AppConfig.DEBUG_MODE) {
    System.out.println("Debug: " + message);
}
```

#### Utiliser le debugger

**IntelliJ IDEA**:
1. Placer des breakpoints (clic dans la marge)
2. Cliquer sur Debug (icône insecte)
3. Inspecter les variables

**VS Code**:
1. Installer l'extension Java Debug
2. Configurer `launch.json`
3. F5 pour débugger

---

### 📱 Problèmes de Performance

#### Application lente au démarrage

**Solutions**:
1. Vérifier la connexion au backend
2. Réduire les animations d'entrée
3. Charger les données en lazy loading

---

#### Tables lentes avec beaucoup de données

**Solutions**:
1. Implémenter la pagination
2. Utiliser la virtualisation (déjà incluse dans TableView)
3. Limiter les colonnes affichées

---

### 🔍 Vérifications Générales

#### Checklist de dépannage

- [ ] Java 17+ installé: `java -version`
- [ ] Maven installé: `mvn -version`
- [ ] Backend démarré: `curl http://localhost:8080/api/programmeurs`
- [ ] Dépendances téléchargées: `mvn dependency:resolve`
- [ ] Projet compilé: `mvn clean compile`
- [ ] Pas d'erreurs dans les logs
- [ ] Fichiers FXML dans resources/
- [ ] CSS dans resources/
- [ ] module-info.java correct

---

### 📞 Obtenir de l'Aide

Si aucune solution ne fonctionne:

1. **Consulter les logs**:
   - Console Maven
   - Logs du backend
   - Stack traces

2. **Vérifier la documentation**:
   - README.md
   - API.md
   - QUICKSTART.md

3. **Créer une issue** avec:
   - Version de Java
   - Système d'exploitation
   - Message d'erreur complet
   - Étapes pour reproduire
   - Logs pertinents

---

### 🔄 Réinitialisation Complète

En dernier recours:

```bash
# Supprimer tous les fichiers générés
mvn clean

# Supprimer le cache Maven local (optionnel)
rm -rf ~/.m2/repository/com/example/front_devmanager

# Réinstaller
mvn clean install

# Relancer
mvn javafx:run
```

---

**Bon courage avec le debugging ! 🔧**

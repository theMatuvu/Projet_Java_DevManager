# 🤝 Guide de Contribution - DevManager Pro

Merci de votre intérêt pour contribuer à DevManager Pro !

## 📋 Prérequis

- Java 17 ou supérieur
- Maven 3.6+
- Git
- IDE recommandé: IntelliJ IDEA ou Eclipse

## 🚀 Installation pour le Développement

1. **Cloner le repository**
```bash
git clone <repository-url>
cd Front_DevManager
```

2. **Installer les dépendances**
```bash
mvn clean install
```

3. **Lancer l'application en mode développement**
```bash
mvn javafx:run
```

## 🏗️ Structure du Projet

```
src/main/java/com/example/front_devmanager/
├── MainApplication.java         # Point d'entrée
├── Launcher.java               # Alternative launcher
├── config/
│   └── AppConfig.java          # Configuration centralisée
├── controller/
│   ├── ProgrammeursViewController.java
│   └── ProjetsViewController.java
├── model/
│   ├── Programmeur.java        # DTO
│   └── Projet.java             # DTO
└── service/
    └── ApiService.java         # Client HTTP

src/main/resources/com/example/front_devmanager/
├── programmeurs-view.fxml
├── projets-view.fxml
└── styles.css
```

## 🎨 Conventions de Code

### Java
- **Indentation**: 4 espaces
- **Naming**:
  - Classes: `PascalCase`
  - Méthodes: `camelCase`
  - Constantes: `UPPER_SNAKE_CASE`
  - Variables: `camelCase`
- **Commentaires**: JavaDoc pour les méthodes publiques

### CSS
- **Classes**: `kebab-case`
- **Organisation**: Grouper par composant
- **Couleurs**: Utiliser les variables définies dans le guide de design

### FXML
- **IDs**: `camelCase`
- **StyleClass**: `kebab-case`
- **Organisation**: Suivre la structure visuelle

## 🎯 Avant de Commencer

1. **Créer une issue** pour discuter de votre proposition
2. **Créer une branche** depuis `main`:
   ```bash
   git checkout -b feature/nom-de-la-fonctionnalite
   ```
3. **Faire des commits atomiques** avec des messages clairs

## ✅ Checklist avant Pull Request

- [ ] Le code compile sans erreur: `mvn clean compile`
- [ ] L'application se lance: `mvn javafx:run`
- [ ] Le code suit les conventions de style
- [ ] Les commentaires JavaDoc sont à jour
- [ ] Les fichiers de documentation sont mis à jour si nécessaire
- [ ] Pas de code commenté inutile
- [ ] Pas de `System.out.println` de debug

## 📝 Messages de Commit

Format recommandé:
```
<type>: <description courte>

<description détaillée optionnelle>

<références optionnelles>
```

**Types**:
- `feat`: Nouvelle fonctionnalité
- `fix`: Correction de bug
- `style`: Changements de style (CSS, formatage)
- `refactor`: Refactoring de code
- `docs`: Documentation
- `test`: Ajout/modification de tests
- `chore`: Tâches de maintenance

**Exemples**:
```
feat: Ajouter la pagination dans les tableaux

Implémentation d'un système de pagination pour gérer
les grandes listes de programmeurs et projets.

Closes #42
```

```
fix: Corriger l'affichage des dates dans la table projets

Les dates s'affichaient au format US, maintenant en FR.
```

## 🐛 Rapport de Bug

Utilisez le template suivant:

```markdown
**Description**
Description claire et concise du bug.

**Reproduction**
1. Aller à '...'
2. Cliquer sur '...'
3. Observer l'erreur

**Comportement attendu**
Ce qui devrait se passer.

**Captures d'écran**
Si applicable, ajouter des captures d'écran.

**Environnement**
- OS: [Windows/macOS/Linux]
- Java: [version]
- Maven: [version]
```

## 💡 Proposition de Fonctionnalité

```markdown
**Fonctionnalité proposée**
Description claire de la fonctionnalité.

**Motivation**
Pourquoi cette fonctionnalité serait utile.

**Solution proposée**
Comment vous envisagez l'implémentation.

**Alternatives considérées**
Autres approches possibles.
```

## 🎨 Ajouter de Nouveaux Composants

### Nouvelle Vue

1. **Créer le FXML**
   ```xml
   <!-- nouvelle-view.fxml -->
   <VBox xmlns:fx="http://javafx.com/fxml"
         fx:controller="com.example.front_devmanager.controller.NouvelleViewController">
   </VBox>
   ```

2. **Créer le Controller**
   ```java
   public class NouvelleViewController {
       @FXML
       public void initialize() {
           // Initialisation
       }
   }
   ```

3. **Ajouter la navigation dans MainApplication**
   ```java
   private void loadNouvelleView() {
       FXMLLoader loader = new FXMLLoader(
           getClass().getResource("/com/example/front_devmanager/nouvelle-view.fxml")
       );
       VBox view = loader.load();
       loadContentWithAnimation(view);
   }
   ```

### Nouveau Modèle

1. **Créer la classe DTO**
   ```java
   public class NouveauModele {
       private Integer id;
       private String nom;
       // Getters, Setters, Constructeurs
   }
   ```

2. **Ouvrir le modèle à Gson** dans `module-info.java`
   ```java
   opens com.example.front_devmanager.model to com.google.gson;
   ```

### Nouveaux Endpoints API

1. **Ajouter les méthodes dans ApiService**
   ```java
   public List<NouveauModele> getAllNouveauModeles() {
       // Implémentation
   }
   ```

2. **Documenter dans API.md**

## 🧪 Tests

Actuellement, le projet n'a pas de tests automatisés.

**Tests manuels à effectuer**:
1. Lancer le backend
2. Lancer le frontend
3. Tester chaque fonctionnalité:
   - Création
   - Lecture
   - Mise à jour
   - Suppression
4. Vérifier les animations
5. Tester la recherche
6. Vérifier la gestion des erreurs

## 🎯 Priorités de Contribution

**High Priority**:
- [ ] Tests unitaires
- [ ] Tests d'intégration
- [ ] Gestion avancée des erreurs
- [ ] Notifications toast

**Medium Priority**:
- [ ] Mode sombre
- [ ] Export PDF/Excel
- [ ] Graphiques statistiques
- [ ] Pagination

**Low Priority**:
- [ ] Internationalisation (i18n)
- [ ] Raccourcis clavier
- [ ] Thèmes personnalisables

## 📞 Contact

Pour toute question:
- Ouvrir une issue
- Consulter la documentation existante

## 🙏 Remerciements

Merci à tous les contributeurs qui rendent ce projet meilleur !

---

**Happy coding! 🚀**

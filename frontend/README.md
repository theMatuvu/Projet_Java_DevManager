# DevManager Pro - Application JavaFX

Une application JavaFX moderne et élégante pour la gestion des programmeurs et des projets.

## 🎨 Fonctionnalités

### Interface Utilisateur
- **Design moderne** avec gradient purple-blue inspiré des meilleures applications
- **Animations fluides** sur les transitions et interactions
- **Navigation intuitive** avec sidebar élégante
- **Dashboard** avec cartes statistiques animées
- **Thème cohérent** avec palette de couleurs harmonieuse

### Gestion des Programmeurs
- ✅ Affichage de tous les programmeurs en tableau
- ✅ Recherche et filtrage en temps réel
- ✅ Création de nouveaux programmeurs
- ✅ Modification du salaire
- ✅ Suppression de programmeurs
- ✅ Colonnes triables et redimensionnables

### Gestion des Projets
- ✅ Affichage de tous les projets en tableau
- ✅ Recherche et filtrage en temps réel
- ✅ Création de nouveaux projets
- ✅ Modification complète des projets
- ✅ Suppression de projets
- ✅ États visuels avec badges colorés (Planifié, En cours, Terminé, Annulé)

## 🚀 Démarrage

### Prérequis
- Java 17 ou supérieur
- Maven 3.6+
- Backend DevManager en cours d'exécution sur `http://localhost:8080`

### Installation

1. **Cloner le projet**
```bash
cd Front_DevManager
```

2. **Compiler le projet**
```bash
mvn clean compile
```

3. **Lancer l'application**
```bash
mvn javafx:run
```

## 🎯 Architecture

```
Front_DevManager/
├── src/main/java/com/example/front_devmanager/
│   ├── MainApplication.java           # Point d'entrée avec navigation
│   ├── controller/
│   │   ├── ProgrammeursViewController.java
│   │   └── ProjetsViewController.java
│   ├── model/
│   │   ├── Programmeur.java           # DTO Programmeur
│   │   └── Projet.java                # DTO Projet
│   └── service/
│       └── ApiService.java            # Client HTTP pour l'API
├── src/main/resources/com/example/front_devmanager/
│   ├── programmeurs-view.fxml
│   ├── projets-view.fxml
│   └── styles.css                     # Styles CSS modernes
└── module-info.java
```

## 🎨 Palette de Couleurs

- **Gradient Principal**: Purple-Blue (#667eea → #764ba2)
- **Gradient Secondaire**: Pink-Orange (#f093fb → #f5576c)
- **Gradient Tertiaire**: Blue (#4facfe → #00f2fe)
- **Background**: Light Gray (#f8f9fa → #e9ecef)
- **Text**: Dark (#2d3748)

## 🔌 API Endpoints Utilisés

### Programmeurs
- `GET /api/programmeurs` - Liste tous les programmeurs
- `GET /api/programmeurs/{id}` - Récupère un programmeur
- `POST /api/programmeurs` - Crée un programmeur
- `PUT /api/programmeurs/{id}/salaire` - Met à jour le salaire
- `DELETE /api/programmeurs/{id}` - Supprime un programmeur

### Projets
- `GET /api/projets` - Liste tous les projets
- `GET /api/projets/{id}` - Récupère un projet
- `POST /api/projets` - Crée un projet
- `PUT /api/projets/{id}` - Met à jour un projet
- `DELETE /api/projets/{id}` - Supprime un projet

## ✨ Fonctionnalités Visuelles

### Animations
- Fade in/out lors des changements de vue
- Scale animation sur les boutons au survol
- Slide animation pour la sidebar au démarrage
- Translation animation sur les cartes statistiques

### Interactions
- Hover effects sur tous les éléments interactifs
- Focus visuel sur les champs de formulaire
- Feedback visuel sur les actions (confirmations, erreurs)
- Tables avec lignes alternées et effet hover

## 🛠️ Technologies

- **JavaFX 22** - Framework UI
- **Ikonli** - Icônes FontAwesome et Material
- **Gson** - Sérialisation/Désérialisation JSON
- **Java HTTP Client** - Appels API REST
- **ControlsFX** - Composants UI avancés
- **CSS3** - Styling moderne avec gradients et animations

## 📝 Notes

- L'application se connecte par défaut à `http://localhost:8080/api`
- Assurez-vous que le backend est démarré avant de lancer l'application
- Les modifications sont immédiatement reflétées dans l'interface
- Le design est responsive et s'adapte à différentes tailles d'écran

## 🎯 Prochaines Améliorations Possibles

- [ ] Gestion des programmeurs assignés aux projets
- [ ] Graphiques et statistiques avancées
- [ ] Export des données (PDF, Excel)
- [ ] Mode sombre/clair
- [ ] Notifications toast élégantes
- [ ] Pagination pour les grandes listes
- [ ] Filtres avancés
- [ ] Tri multi-colonnes

---

**DevManager Pro** - Une application moderne pour une gestion efficace ! 🚀

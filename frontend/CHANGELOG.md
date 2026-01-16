# 📋 Changelog

Toutes les modifications notables de ce projet seront documentées dans ce fichier.

Le format est basé sur [Keep a Changelog](https://keepachangelog.com/fr/1.0.0/),
et ce projet adhère au [Semantic Versioning](https://semver.org/lang/fr/).

## [1.0.0] - 2026-01-15

### ✨ Ajouté
- Interface utilisateur moderne avec design gradient Purple-Blue
- Navigation avec sidebar élégante et animations fluides
- Dashboard avec cartes statistiques animées
- Gestion complète des programmeurs (CRUD)
  - Affichage en tableau avec colonnes triables
  - Création de nouveaux programmeurs
  - Modification du salaire
  - Suppression avec confirmation
  - Recherche en temps réel
- Gestion complète des projets (CRUD)
  - Affichage en tableau avec états colorés
  - Création de nouveaux projets
  - Modification complète des projets
  - Suppression avec confirmation
  - Recherche en temps réel
- Service API pour communication avec le backend
  - GET, POST, PUT, DELETE pour programmeurs
  - GET, POST, PUT, DELETE pour projets
  - Gestion automatique des dates (LocalDate)
  - Gestion des erreurs HTTP
- Système de configuration centralisée (AppConfig)
- Animations et transitions
  - Fade in/out sur changement de vue
  - Scale effect sur les boutons
  - Slide-in pour la sidebar
  - Hover effects sur tous les éléments interactifs
- Design responsive
  - Tables avec redimensionnement automatique
  - Scrollbars personnalisées
  - Adaptation à différentes tailles d'écran
- Documentation complète
  - README.md avec guide complet
  - QUICKSTART.md pour démarrage rapide
  - API.md pour documentation des endpoints
  - DESIGN.md pour guide visuel
  - CONTRIBUTING.md pour les contributeurs
- Scripts de lancement
  - launch.bat pour Windows
  - launch.sh pour Linux/macOS

### 🎨 Style
- Palette de couleurs moderne et cohérente
  - Primary: Purple-Blue gradient (#667eea → #764ba2)
  - Secondary: Pink-Orange gradient (#f093fb → #f5576c)
  - Tertiary: Light Blue gradient (#4facfe → #00f2fe)
- Typographie claire et hiérarchisée
- Ombres et effets de profondeur
- Border radius harmonieux
- États visuels pour les projets (badges colorés)

### 🛠️ Technique
- JavaFX 22 pour l'interface utilisateur
- Gson 2.10.1 pour la sérialisation JSON
- Ikonli pour les icônes FontAwesome et Material
- Java HTTP Client pour les appels REST
- Architecture MVC (Model-View-Controller)
- Module Java avec exports appropriés
- Configuration Maven avec plugin JavaFX

### 📦 Dépendances
- javafx-controls 22.0.1
- javafx-fxml 22.0.1
- javafx-web 22.0.1
- gson 2.10.1
- ikonli-javafx 12.3.1
- ikonli-fontawesome5-pack 12.3.1
- ikonli-material-pack 12.3.1
- controlsfx 11.2.1
- formsfx-core 11.6.0
- validatorfx 0.6.1
- bootstrapfx-core 0.4.0
- tilesfx 21.0.9

### 🔧 Configuration
- URL API configurable via AppConfig
- Timeout HTTP personnalisable
- Dimensions de fenêtre configurables
- Mode debug activable

### 📝 Documentation
- Commentaires JavaDoc sur les méthodes principales
- README complet avec guide d'utilisation
- Documentation de l'architecture
- Guide de démarrage rapide
- Documentation de l'API
- Guide de design visuel
- Guide de contribution

---

## [Unreleased]

### 🎯 Prévu
- [ ] Tests unitaires avec JUnit 5
- [ ] Tests d'intégration
- [ ] Notifications toast élégantes
- [ ] Mode sombre/clair
- [ ] Export PDF/Excel
- [ ] Graphiques et statistiques avancées
- [ ] Pagination pour grandes listes
- [ ] Filtres avancés
- [ ] Tri multi-colonnes
- [ ] Gestion des programmeurs assignés aux projets
- [ ] Internationalisation (i18n)
- [ ] Raccourcis clavier
- [ ] Thèmes personnalisables
- [ ] Cache local pour mode hors-ligne
- [ ] Historique des modifications
- [ ] Logs d'activité

---

## Types de Modifications

- **✨ Ajouté** : Nouvelles fonctionnalités
- **🔄 Modifié** : Changements dans les fonctionnalités existantes
- **⚠️ Déprécié** : Fonctionnalités bientôt supprimées
- **🗑️ Supprimé** : Fonctionnalités supprimées
- **🐛 Corrigé** : Corrections de bugs
- **🔒 Sécurité** : Corrections de vulnérabilités
- **🎨 Style** : Changements visuels
- **🛠️ Technique** : Changements techniques sans impact utilisateur
- **📝 Documentation** : Changements de documentation

---

**DevManager Pro - Changelog** 📋

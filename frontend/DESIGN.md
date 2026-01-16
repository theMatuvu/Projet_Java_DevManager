# 🎨 Guide Visuel DevManager Pro

## Palette de Couleurs

### Couleurs Principales
- **Primary Gradient**: `#667eea` → `#764ba2` (Purple-Blue)
  - Utilisé pour: Sidebar, boutons principaux, en-têtes de tableaux
  
- **Secondary Gradient**: `#f093fb` → `#f5576c` (Pink-Orange)
  - Utilisé pour: Boutons de suppression, alertes
  
- **Tertiary Gradient**: `#4facfe` → `#00f2fe` (Light Blue)
  - Utilisé pour: Boutons secondaires, accents

### Couleurs de Fond
- **Background Principal**: `#f8f9fa` → `#e9ecef` (Light Gray Gradient)
- **Cards/Tables**: `#ffffff` (White)
- **Hover**: `#e6f2ff` (Light Blue)

### Couleurs de Texte
- **Primaire**: `#2d3748` (Dark Gray)
- **Secondaire**: `#718096` (Medium Gray)
- **Sur Gradient**: `#ffffff` (White)

## Éléments Visuels

### 1. Sidebar (Barre Latérale)
```
┌─────────────────────────┐
│  🖥️ DevManager         │
│                         │
│  📊 Dashboard           │
│  👥 Programmeurs        │
│  📋 Projets             │
│                         │
│  ⚙️ Paramètres          │
└─────────────────────────┘
```
- Largeur: 250px
- Background: Gradient Purple-Blue
- Effet: Drop shadow
- Animation: Slide-in depuis la gauche

### 2. Dashboard
```
┌─────────────────────────────────────────────┐
│  Bienvenue sur DevManager Pro               │
│                                             │
│  ┌─────────┐  ┌─────────┐  ┌─────────┐    │
│  │👥       │  │📋       │  │⏳       │    │
│  │   0     │  │   0     │  │   0     │    │
│  │Progr.   │  │Projets  │  │En cours │    │
│  └─────────┘  └─────────┘  └─────────┘    │
└─────────────────────────────────────────────┘
```
- Cartes: 300x180px
- Border: Couleur du gradient (2px)
- Effet: Lift on hover (-10px translateY)
- Animation: Scale et drop shadow

### 3. Vue Programmeurs
```
┌─────────────────────────────────────────────┐
│  Gestion des Programmeurs        🔍 [____] │
│  [+ Nouveau] [🔄 Actualiser]              │
│                                             │
│  ┌───────────────────────────────────────┐ │
│  │ ID │ Nom │ Prénom │ ... │ Actions   │ │
│  ├───────────────────────────────────────┤ │
│  │ 1  │ Doe │ John   │ ... │ ✏️ 💰 🗑️ │ │
│  │ 2  │...  │...     │ ... │ ✏️ 💰 🗑️ │ │
│  └───────────────────────────────────────┘ │
└─────────────────────────────────────────────┘
```
- Header: Gradient Purple-Blue
- Rows: Alternating white/#f7fafc
- Hover: Light blue background
- Actions: Colored icon buttons

### 4. Vue Projets
```
┌─────────────────────────────────────────────┐
│  Gestion des Projets             🔍 [____] │
│  [+ Nouveau] [🔄 Actualiser]              │
│                                             │
│  ┌───────────────────────────────────────┐ │
│  │ ID │ Intitulé │ État │ ... │ Actions │ │
│  ├───────────────────────────────────────┤ │
│  │ 1  │ Projet A │🟢En cours│...│✏️ 🗑️│ │
│  │ 2  │ Projet B │🔵Planifié│...│✏️ 🗑️│ │
│  └───────────────────────────────────────┘ │
└─────────────────────────────────────────────┘
```
- États avec badges colorés:
  - 🟢 En cours: `#4facfe`
  - 🔵 Planifié: `#667eea`
  - ✅ Terminé: `#43e97b`
  - ⭕ Annulé: `#f093fb`

## Animations

### 1. Entrée de l'Application
- Sidebar: Slide-in from left (400ms)
- Content: Fade-in (600ms, delay 200ms)

### 2. Changement de Vue
- Old content: Fade-out (200ms)
- New content: Fade-in (300ms)

### 3. Interactions
- Boutons: Scale 1.05 on hover
- Cartes: TranslateY -10px on hover
- Actions: Scale 1.1 on hover

### 4. Tables
- Rows: Smooth background transition
- Scroll: Smooth scrolling

## Typographie

### Polices
- Principale: "Segoe UI", "Helvetica Neue", Arial, sans-serif
- Taille de base: 14px

### Hiérarchie
- **App Title**: 24px, Bold, White
- **Page Title**: 32px, Bold, `#2d3748`
- **Stat Value**: 48px, Bold, Gradient color
- **Stat Title**: 18px, `#718096`
- **Body**: 14px, `#2d3748`
- **Buttons**: 14px, Bold

## Espacement

### Padding
- Container principal: 30px
- Cartes: 30px
- Boutons: 12px vertical, 25px horizontal
- Table cells: 10px vertical, 15px horizontal

### Spacing
- Entre sections: 20-30px
- Entre éléments: 15px
- Entre boutons: 8-15px

## Effets

### Ombres
- **Cards**: `dropshadow(gaussian, rgba(0,0,0,0.1), 20, 0, 0, 5)`
- **Hover Cards**: `dropshadow(gaussian, rgba(0,0,0,0.2), 30, 0, 0, 10)`
- **Buttons**: `dropshadow(gaussian, rgba(102,126,234,0.4), 10, 0, 0, 3)`
- **Sidebar**: `dropshadow(gaussian, rgba(0,0,0,0.3), 15, 0, 5, 0)`

### Border Radius
- **Cards**: 20px
- **Buttons**: 10px
- **Inputs**: 8px (forms) / 25px (search)
- **Tables**: 15px

## Responsive Design

### Tailles de Fenêtre
- Minimum: 1200x800
- Recommandé: 1400x900
- Maximum: Illimité (s'adapte)

### Colonnes de Table
- Auto-resize avec CONSTRAINED_RESIZE_POLICY
- Largeurs minimales préservées
- Horizontal scroll si nécessaire

---

**Design moderne et professionnel pour une expérience utilisateur optimale ! ✨**

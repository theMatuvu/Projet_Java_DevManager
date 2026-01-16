# 📡 Documentation API - DevManager Pro

## Configuration

L'URL de base de l'API est configurable dans `AppConfig.java` :
```java
public static final String API_BASE_URL = "http://localhost:8080/api";
```

## Endpoints Programmeurs

### 📋 GET /api/programmeurs
**Description**: Récupère la liste de tous les programmeurs

**Réponse**: 
```json
[
  {
    "id": 1,
    "nom": "Doe",
    "prenom": "John",
    "pseudo": "jdoe",
    "adresse": "123 Main St",
    "responsable": "Manager Name",
    "hobby": "Gaming",
    "naissance": 1990,
    "salaire": 50000.0,
    "prime": 5000.0
  }
]
```

**Utilisation dans l'app**: Chargement initial et actualisation de la table

---

### 🔍 GET /api/programmeurs/{id}
**Description**: Récupère un programmeur spécifique par son ID

**Paramètres**: 
- `id` (path): ID du programmeur

**Réponse**: Objet Programmeur ou 404 si non trouvé

**Utilisation dans l'app**: Vérification avant modification/suppression

---

### ➕ POST /api/programmeurs
**Description**: Crée un nouveau programmeur

**Body**:
```json
{
  "id": 3,
  "nom": "Smith",
  "prenom": "Alice",
  "pseudo": "asmith",
  "adresse": "456 Oak Ave",
  "responsable": "Team Lead",
  "hobby": "Reading",
  "naissance": 1995,
  "salaire": 55000.0,
  "prime": 6000.0
}
```

**Réponse**: 
- 201 Created: "Programmeur créé avec succès"
- 500 Internal Server Error: Message d'erreur

**Utilisation dans l'app**: Bouton "Nouveau Programmeur" → Dialog → Création

---

### 💰 PUT /api/programmeurs/{id}/salaire
**Description**: Met à jour le salaire d'un programmeur

**Paramètres**:
- `id` (path): ID du programmeur
- `salaire` (query): Nouveau salaire

**Exemple**: `/api/programmeurs/1/salaire?salaire=60000.0`

**Réponse**:
- 200 OK: "Salaire mis à jour avec succès"
- 404 Not Found: Programmeur non trouvé
- 500 Internal Server Error: Message d'erreur

**Utilisation dans l'app**: Icône 💰 → Dialog → Mise à jour

---

### 🗑️ DELETE /api/programmeurs/{id}
**Description**: Supprime un programmeur

**Paramètres**:
- `id` (path): ID du programmeur

**Réponse**:
- 200 OK: "Programmeur supprimé avec succès"
- 404 Not Found: Programmeur non trouvé
- 500 Internal Server Error: Message d'erreur

**Utilisation dans l'app**: Icône 🗑️ → Confirmation → Suppression

---

## Endpoints Projets

### 📋 GET /api/projets
**Description**: Récupère la liste de tous les projets

**Réponse**:
```json
[
  {
    "id": 1,
    "intitule": "Projet Web",
    "dateDebut": "2024-01-15",
    "dateFinPrevue": "2024-06-15",
    "etat": "En cours",
    "programmeurs": []
  }
]
```

**Utilisation dans l'app**: Chargement initial et actualisation de la table

---

### 🔍 GET /api/projets/{id}
**Description**: Récupère un projet spécifique par son ID

**Paramètres**:
- `id` (path): ID du projet

**Réponse**: Objet Projet ou 404 si non trouvé

**Utilisation dans l'app**: Vérification avant modification/suppression

---

### ➕ POST /api/projets
**Description**: Crée un nouveau projet

**Body**:
```json
{
  "intitule": "Nouveau Projet",
  "dateDebut": "2024-02-01",
  "dateFinPrevue": "2024-08-01",
  "etat": "Planifié"
}
```

**Réponse**:
- 201 Created: "Projet créé avec succès"
- 500 Internal Server Error: Message d'erreur

**Utilisation dans l'app**: Bouton "Nouveau Projet" → Dialog → Création

---

### ✏️ PUT /api/projets/{id}
**Description**: Met à jour un projet complet

**Paramètres**:
- `id` (path): ID du projet

**Body**: Objet Projet complet avec modifications

**Réponse**:
- 200 OK: "Projet mis à jour avec succès"
- 404 Not Found: Projet non trouvé
- 500 Internal Server Error: Message d'erreur

**Utilisation dans l'app**: Icône ✏️ → Dialog → Mise à jour

---

### 🗑️ DELETE /api/projets/{id}
**Description**: Supprime un projet

**Paramètres**:
- `id` (path): ID du projet

**Réponse**:
- 200 OK: "Projet supprimé avec succès"
- 404 Not Found: Projet non trouvé
- 500 Internal Server Error: Message d'erreur

**Utilisation dans l'app**: Icône 🗑️ → Confirmation → Suppression

---

## Format des Dates

Les dates sont échangées au format ISO 8601: `YYYY-MM-DD`

**Exemple**: `2024-01-15`

Le service `ApiService` gère automatiquement la conversion entre:
- `String` (JSON) ↔️ `LocalDate` (Java)

---

## Gestion des Erreurs

### Dans ApiService.java

Toutes les méthodes peuvent lancer:
- `IOException`: Erreur de connexion/réseau
- `InterruptedException`: Thread interrompu

**Exemples de codes d'erreur HTTP**:
- `200 OK`: Succès
- `201 Created`: Ressource créée
- `404 Not Found`: Ressource inexistante
- `500 Internal Server Error`: Erreur serveur

### Dans les Controllers

Les erreurs sont capturées et affichées via:
```java
showErrorAlert("Erreur", "Message d'erreur détaillé");
```

---

## CORS

Le backend autorise les requêtes depuis n'importe quelle origine:
```java
@CrossOrigin(origins = "*")
```

Cela permet à l'application JavaFX de communiquer avec l'API sans restriction.

---

## Timeout

Le timeout par défaut est configuré dans `AppConfig.java`:
```java
public static final int HTTP_TIMEOUT = 30; // secondes
```

---

## Format JSON

### Programmeur
```json
{
  "id": number,
  "nom": string,
  "prenom": string,
  "pseudo": string,
  "adresse": string,
  "responsable": string,
  "hobby": string,
  "naissance": number (année),
  "salaire": number (decimal),
  "prime": number (decimal)
}
```

### Projet
```json
{
  "id": number,
  "intitule": string,
  "dateDebut": string (YYYY-MM-DD),
  "dateFinPrevue": string (YYYY-MM-DD),
  "etat": string (Planifié|En cours|Terminé|Annulé),
  "programmeurs": array (optionnel)
}
```

---

## Testing avec curl

### Tester la connexion
```bash
curl http://localhost:8080/api/programmeurs
```

### Créer un programmeur
```bash
curl -X POST http://localhost:8080/api/programmeurs \
  -H "Content-Type: application/json" \
  -d '{"id":99,"nom":"Test","prenom":"User","pseudo":"tuser","adresse":"Test St","responsable":"Boss","hobby":"Testing","naissance":2000,"salaire":40000,"prime":4000}'
```

### Mettre à jour un salaire
```bash
curl -X PUT "http://localhost:8080/api/programmeurs/1/salaire?salaire=70000"
```

### Supprimer un programmeur
```bash
curl -X DELETE http://localhost:8080/api/programmeurs/99
```

---

**Documentation complète de l'intégration API ! 📡**

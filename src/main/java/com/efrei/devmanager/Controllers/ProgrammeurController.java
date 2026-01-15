package com.efrei.devmanager.Controllers;

import com.efrei.devmanager.ActionsBDD;
import com.efrei.devmanager.Programmeur;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/programmeurs")
@CrossOrigin(origins = "*") // Permet l'accès depuis n'importe quelle origine (frontend externe)
public class ProgrammeurController {

    private final ActionsBDD actionsBDD;

    public ProgrammeurController(ActionsBDD actionsBDD) {
        this.actionsBDD = actionsBDD;
    }

    /**
     * GET /api/programmeurs
     * Récupère la liste de tous les programmeurs
     */
    @GetMapping
    public ResponseEntity<List<Programmeur>> getAllProgrammeurs() {
        List<Programmeur> programmeurs = actionsBDD.getProgrammeurs();
        return ResponseEntity.ok(programmeurs);
    }

    /**
     * GET /api/programmeurs/{id}
     * Récupère un programmeur par son ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Programmeur> getProgrammeurById(@PathVariable int id) {
        Programmeur programmeur = actionsBDD.getProgrammeur(id);
        if (programmeur == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(programmeur);
    }

    /**
     * POST /api/programmeurs
     * Crée un nouveau programmeur
     */
    @PostMapping
    public ResponseEntity<String> createProgrammeur(@RequestBody Programmeur programmeur) {
        try {
            actionsBDD.insertProgrammeur(programmeur);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Programmeur créé avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la création du programmeur: " + e.getMessage());
        }
    }

    /**
     * PUT /api/programmeurs/{id}/salaire
     * Met à jour le salaire d'un programmeur
     */
    @PutMapping("/{id}/salaire")
    public ResponseEntity<String> updateProgrammeurSalaire(
            @PathVariable int id,
            @RequestParam double salaire) {
        try {
            Programmeur programmeur = actionsBDD.getProgrammeur(id);
            if (programmeur == null) {
                return ResponseEntity.notFound().build();
            }
            actionsBDD.updateProgrammeurSalaire(id, salaire);
            return ResponseEntity.ok("Salaire mis à jour avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la mise à jour du salaire: " + e.getMessage());
        }
    }

    /**
     * DELETE /api/programmeurs/{id}
     * Supprime un programmeur
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProgrammeur(@PathVariable int id) {
        try {
            Programmeur programmeur = actionsBDD.getProgrammeur(id);
            if (programmeur == null) {
                return ResponseEntity.notFound().build();
            }
            actionsBDD.deleteProgrammeur(id);
            return ResponseEntity.ok("Programmeur supprimé avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la suppression du programmeur: " + e.getMessage());
        }
    }
}

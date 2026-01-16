package com.efrei.devmanager.Controllers;

import com.efrei.devmanager.Programmeur;
import com.efrei.devmanager.RestImplementation;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/programmeurs")
@CrossOrigin(origins = "*") // Permet l'accès depuis n'importe quelle origine (frontend externe)
public class ProgrammeurController {

    private final RestImplementation restImplementation;

    public ProgrammeurController(@Qualifier("restImplementation") RestImplementation restImplementation) {
        this.restImplementation = restImplementation;
    }

    /**
     * GET /api/programmeurs
     * Récupère la liste de tous les programmeurs
     */
    @GetMapping
    public ResponseEntity<List<Programmeur>> getAllProgrammeurs() {
        List<Programmeur> programmeurs = restImplementation.getAllProgrammeurs();
        return ResponseEntity.ok(programmeurs);
    }

    /**
     * GET /api/programmeurs/{id}
     * Récupère un programmeur par son ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Programmeur> getProgrammeurById(@PathVariable int id) {
        Programmeur programmeur = restImplementation.getProgrammeurById(id);
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
            restImplementation.addProgrammeur(programmeur);
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
            Programmeur programmeur = restImplementation.getProgrammeurById(id);
            if (programmeur == null) {
                return ResponseEntity.notFound().build();
            }
            restImplementation.updateProgrammeurSalaire(id, salaire);
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
            Programmeur programmeur = restImplementation.getProgrammeurById(id);
            if (programmeur == null) {
                return ResponseEntity.notFound().build();
            }
            restImplementation.deleteProgrammeur(id);
            return ResponseEntity.ok("Programmeur supprimé avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la suppression du programmeur: " + e.getMessage());
        }
    }
    /**
     * GET /api/programmeurs/{id}/projets
     * Récupère tous les projets d'un programmeur
     */
    @GetMapping("/{id}/projets")
    public ResponseEntity<List<com.efrei.devmanager.Projet>> getProjetsByProgrammeur(@PathVariable int id) {
        try {
            List<com.efrei.devmanager.Projet> projets = restImplementation.getProjetsByProgrammeur(id);
            return ResponseEntity.ok(projets);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }}

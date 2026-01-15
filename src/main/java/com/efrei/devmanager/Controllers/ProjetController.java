package com.efrei.devmanager.Controllers;

import com.efrei.devmanager.Programmeur;
import com.efrei.devmanager.Projet;
import com.efrei.devmanager.RestImplementation;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projets")
@CrossOrigin(origins = "*")
public class ProjetController {

    private final RestImplementation restImplementation;

    public ProjetController(@Qualifier("restImplementation") RestImplementation restImplementation) {
        this.restImplementation = restImplementation;
    }

    /**
     * GET /api/projets
     * Récupère la liste de tous les projets
     */
    @GetMapping
    public ResponseEntity<List<Projet>> getAllProjets() {
        List<Projet> projets = restImplementation.getAllProjets();
        return ResponseEntity.ok(projets);
    }

    /**
     * GET /api/projets/{id}
     * Récupère un projet par son ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Projet> getProjetById(@PathVariable int id) {
        Projet projet = restImplementation.getProjetById(id);
        if (projet == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(projet);
    }

    /**
     * POST /api/projets
     * Crée un nouveau projet
     */
    @PostMapping
    public ResponseEntity<String> createProjet(@RequestBody Projet projet) {
        try {
            restImplementation.addProjet(projet);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Projet créé avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la création du projet: " + e.getMessage());
        }
    }

    /**
     * PUT /api/projets/{id}
     * Met à jour un projet
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> updateProjet(@PathVariable int id, @RequestBody Projet projet) {
        try {
            Projet existingProjet = restImplementation.getProjetById(id);
            if (existingProjet == null) {
                return ResponseEntity.notFound().build();
            }
            projet.setId(id);
            restImplementation.updateProjet(projet);
            return ResponseEntity.ok("Projet mis à jour avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la mise à jour du projet: " + e.getMessage());
        }
    }

    /**
     * DELETE /api/projets/{id}
     * Supprime un projet
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProjet(@PathVariable int id) {
        try {
            Projet projet = restImplementation.getProjetById(id);
            if (projet == null) {
                return ResponseEntity.notFound().build();
            }
            restImplementation.deleteProjet(id);
            return ResponseEntity.ok("Projet supprimé avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la suppression du projet: " + e.getMessage());
        }
    }

    /**
     * POST /api/projets/{projetId}/programmeurs/{programmeurId}
     * Assigne un programmeur à un projet
     */
    @PostMapping("/{projetId}/programmeurs/{programmeurId}")
    public ResponseEntity<String> assignProgrammeurToProjet(
            @PathVariable int projetId,
            @PathVariable int programmeurId) {
        try {
            restImplementation.assignProgrammeurToProjet(programmeurId, projetId);
            return ResponseEntity.ok("Programmeur assigné au projet avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de l'assignation: " + e.getMessage());
        }
    }

    /**
     * DELETE /api/projets/{projetId}/programmeurs/{programmeurId}
     * Retire un programmeur d'un projet
     */
    @DeleteMapping("/{projetId}/programmeurs/{programmeurId}")
    public ResponseEntity<String> removeProgrammeurFromProjet(
            @PathVariable int projetId,
            @PathVariable int programmeurId) {
        try {
            restImplementation.removeProgrammeurFromProjet(programmeurId, projetId);
            return ResponseEntity.ok("Programmeur retiré du projet avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors du retrait: " + e.getMessage());
        }
    }

    /**
     * GET /api/projets/{id}/programmeurs
     * Récupère tous les programmeurs travaillant sur un projet
     */
    @GetMapping("/{id}/programmeurs")
    public ResponseEntity<List<Programmeur>> getProgrammeursByProjet(@PathVariable int id) {
        try {
            List<Programmeur> programmeurs = restImplementation.getProgrammeursByProjet(id);
            return ResponseEntity.ok(programmeurs);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

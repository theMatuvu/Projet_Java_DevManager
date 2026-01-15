package com.efrei.devmanager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjetJpa extends JpaRepository<Projet, Integer> {
    
    // Trouver tous les programmeurs qui travaillent sur un projet spécifique
    @Query("SELECT p.programmeurs FROM Projet p WHERE p.id = :projetId")
    List<Programmeur> findProgrammeursByProjetId(@Param("projetId") Integer projetId);
    
    // Trouver tous les projets d'un programmeur
    @Query("SELECT proj FROM Projet proj JOIN proj.programmeurs prog WHERE prog.id = :programmeurId")
    List<Projet> findProjetsByProgrammeurId(@Param("programmeurId") Integer programmeurId);
}

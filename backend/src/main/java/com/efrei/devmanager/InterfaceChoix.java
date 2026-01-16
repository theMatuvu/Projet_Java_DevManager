package com.efrei.devmanager;

import java.util.List;

public interface InterfaceChoix {
    // Méthodes Programmeur
    List<Programmeur> getAllProgrammeurs();
    Programmeur getProgrammeurById(int id);
    void deleteProgrammeur(int id);
    void addProgrammeur(Programmeur programmeur);
    void updateProgrammeurSalaire(int id, double salaire);
    
    // Méthodes Projet
    List<Projet> getAllProjets();
    Projet getProjetById(int id);
    void deleteProjet(int id);
    void addProjet(Projet projet);
    void updateProjet(Projet projet);
    
    // Méthodes relation
    void assignProgrammeurToProjet(int programmeurId, int projetId);
    void removeProgrammeurFromProjet(int programmeurId, int projetId);
    List<Programmeur> getProgrammeursByProjet(int projetId);
    List<Projet> getProjetsByProgrammeur(int programmeurId);
}

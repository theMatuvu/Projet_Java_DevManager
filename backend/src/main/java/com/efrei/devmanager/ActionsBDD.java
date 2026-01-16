package com.efrei.devmanager;

import java.util.List;

public interface ActionsBDD {

    // Méthodes Programmeur
    public void insertProgrammeur(Programmeur programmeur);
    public List<Programmeur> getProgrammeurs();
    public Programmeur getProgrammeur(int id);
    void deleteProgrammeur(int id);
    void updateProgrammeurSalaire(int idProgrammeur, double salaire);
    
    // Méthodes Projet
    public void insertProjet(Projet projet);
    public List<Projet> getProjets();
    public Projet getProjet(int id);
    void deleteProjet(int id);
    void updateProjet(Projet projet);
    
    // Méthodes relation Programmeur-Projet
    void assignProgrammeurToProjet(int programmeurId, int projetId);
    void removeProgrammeurFromProjet(int programmeurId, int projetId);
    List<Programmeur> getProgrammeursByProjet(int projetId);
    List<Projet> getProjetsByProgrammeur(int programmeurId);
}

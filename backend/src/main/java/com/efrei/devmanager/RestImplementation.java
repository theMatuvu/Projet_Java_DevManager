package com.efrei.devmanager;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implémentation REST de InterfaceChoix
 * Cette classe adapte les opérations pour une utilisation via API REST
 */
@Service("restImplementation")
public class RestImplementation implements InterfaceChoix {
    
    private final ActionsBDD actionsBDD;

    public RestImplementation(ActionsBDD actionsBDD) {
        this.actionsBDD = actionsBDD;
    }


    @Override
    public List<Programmeur> getAllProgrammeurs() {
        return actionsBDD.getProgrammeurs();
    }

    @Override
    public Programmeur getProgrammeurById(int id) {
        return actionsBDD.getProgrammeur(id);
    }

    @Override
    public void deleteProgrammeur(int id) {
        actionsBDD.deleteProgrammeur(id);
    }

    @Override
    public void addProgrammeur(Programmeur programmeur) {
        actionsBDD.insertProgrammeur(programmeur);
    }

    @Override
    public void updateProgrammeurSalaire(int id, double salaire) {
        actionsBDD.updateProgrammeurSalaire(id, salaire);
    }
    

    @Override
    public List<Projet> getAllProjets() {
        return actionsBDD.getProjets();
    }

    @Override
    public Projet getProjetById(int id) {
        return actionsBDD.getProjet(id);
    }

    @Override
    public void deleteProjet(int id) {
        actionsBDD.deleteProjet(id);
    }

    @Override
    public void addProjet(Projet projet) {
        actionsBDD.insertProjet(projet);
    }

    @Override
    public void updateProjet(Projet projet) {
        actionsBDD.updateProjet(projet);
    }
    

    @Override
    public void assignProgrammeurToProjet(int programmeurId, int projetId) {
        actionsBDD.assignProgrammeurToProjet(programmeurId, projetId);
    }

    @Override
    public void removeProgrammeurFromProjet(int programmeurId, int projetId) {
        actionsBDD.removeProgrammeurFromProjet(programmeurId, projetId);
    }

    @Override
    public List<Programmeur> getProgrammeursByProjet(int projetId) {
        return actionsBDD.getProgrammeursByProjet(projetId);
    }

    @Override
    public List<Projet> getProjetsByProgrammeur(int programmeurId) {
        return actionsBDD.getProjetsByProgrammeur(programmeurId);
    }
}

package com.efrei.devmanager;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ActionsBDDImpl implements ActionsBDD{

    private final ProgrammeurJpa programmeurJpa;
    private final ProjetJpa projetJpa;

    public ActionsBDDImpl(ProgrammeurJpa programmeurJpa, ProjetJpa projetJpa) {
        this.programmeurJpa = programmeurJpa;
        this.projetJpa = projetJpa;
    }


    @Override
    public void insertProgrammeur(Programmeur programmeur) {
        programmeurJpa.save(programmeur);
    }

    @Override
    public List<Programmeur> getProgrammeurs() {
        return programmeurJpa.findAll();
    }

    @Override
    public Programmeur getProgrammeur(int id) {
        return programmeurJpa.findById(id).orElse(null);
    }

    @Override
    public void deleteProgrammeur(int id) {
        programmeurJpa.deleteById(id);
    }

    @Override
    public void updateProgrammeurSalaire(int programmeurId, double salaire) {
        Programmeur programmeur = programmeurJpa.findById(programmeurId).get();
        programmeur.setSalaire(salaire);
        programmeurJpa.save(programmeur);
    }
    

    @Override
    public void insertProjet(Projet projet) {
        projetJpa.save(projet);
    }

    @Override
    public List<Projet> getProjets() {
        return projetJpa.findAll();
    }

    @Override
    public Projet getProjet(int id) {
        return projetJpa.findById(id).orElse(null);
    }

    @Override
    public void deleteProjet(int id) {
        projetJpa.deleteById(id);
    }

    @Override
    public void updateProjet(Projet projet) {
        projetJpa.save(projet);
    }
    

    @Override
    public void assignProgrammeurToProjet(int programmeurId, int projetId) {
        Programmeur programmeur = programmeurJpa.findById(programmeurId).orElse(null);
        Projet projet = projetJpa.findById(projetId).orElse(null);
        
        if (programmeur != null && projet != null) {
            projet.getProgrammeurs().add(programmeur);
            projetJpa.save(projet);
        }
    }

    @Override
    public void removeProgrammeurFromProjet(int programmeurId, int projetId) {
        Programmeur programmeur = programmeurJpa.findById(programmeurId).orElse(null);
        Projet projet = projetJpa.findById(projetId).orElse(null);
        
        if (programmeur != null && projet != null) {
            projet.getProgrammeurs().remove(programmeur);
            projetJpa.save(projet);
        }
    }

    @Override
    public List<Programmeur> getProgrammeursByProjet(int projetId) {
        return projetJpa.findProgrammeursByProjetId(projetId);
    }

    @Override
    public List<Projet> getProjetsByProgrammeur(int programmeurId) {
        return projetJpa.findProjetsByProgrammeurId(programmeurId);
    }
}

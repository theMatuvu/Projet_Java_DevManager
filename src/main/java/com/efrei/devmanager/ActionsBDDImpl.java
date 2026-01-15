package com.efrei.devmanager;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ActionsBDDImpl implements ActionsBDD{

    public final ProgrammeurJpa programmeurJpa;

    public ActionsBDDImpl(ProgrammeurJpa programmeurJpa) {
        this.programmeurJpa = programmeurJpa;
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
}

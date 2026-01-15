package com.efrei.devmanager;

import java.util.List;

public interface ActionsBDD {

    public void insertProgrammeur(Programmeur programmeur);
    public List<Programmeur> getProgrammeurs();
    public Programmeur getProgrammeur(int id);


    void deleteProgrammeur(int id);

    void updateProgrammeurSalaire(int idProgrammeur, double salaire);
}

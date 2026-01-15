package com.efrei.devmanager;

public interface InterfaceChoix {
    void printProgrammeurs();
    void printProgrammeur(int id);
    void deleteProgrammeur(int id);
    void addProgrammeur(Programmeur programmeur);
    void updateProgrammeurSalaire(int id, double salaire);
    void printProjectList();
    void programmerListByProject(int id);
    void exit();

}

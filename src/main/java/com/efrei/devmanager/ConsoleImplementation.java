package com.efrei.devmanager;

import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class ConsoleImplementation implements InterfaceChoix{
    private final ActionsBDD actionsBDD;

    public ConsoleImplementation(ActionsBDD actionsBDD) {
        this.actionsBDD = actionsBDD;
    }

    @Override
    public void printProgrammeurs() {
        actionsBDD.getProgrammeurs().forEach(System.out::println);
    }

    @Override
    public void printProgrammeur(int id) {
        System.out.println(actionsBDD.getProgrammeur(id));
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
    public void printProjectList() {
        //Afficher la liste des projets


    }

    @Override
    public void programmerListByProject(int id) {
        // À implémenter
    }

    @Override
    public void exit() {
        // Rien à faire ici pour l’instant
    }

}

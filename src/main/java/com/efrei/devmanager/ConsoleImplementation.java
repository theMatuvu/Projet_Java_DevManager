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
    public void printProgrammeur() {
        System.out.println("Entrez l'ID du programmeur : ");
        int id = new java.util.Scanner(System.in).nextInt();
        System.out.println(actionsBDD.getProgrammeur(id));
    }

    @Override
    public void deleteProgrammeur() {
        System.out.println("Entrez l'ID du programmeur : ");
        int id = new java.util.Scanner(System.in).nextInt();
        actionsBDD.deleteProgrammeur(id);
    }

    @Override
    public void addProgrammeur() {
        Programmeur programmeur = createProgrammeur();
        actionsBDD.insertProgrammeur(programmeur);
    }

    @Override
    public void updateProgrammeurSalaire() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.println("Entrez l'ID du programmeur : ");
        int id = scanner.nextInt();
        System.out.println("Entrez le nouveau salaire : ");
        double salaire = scanner.nextDouble();
        actionsBDD.updateProgrammeurSalaire(id, salaire);
    }

    @Override
    public void printProjectList() {
        // À implémenter
    }

    @Override
    public void programmerListByProject() {
        // À implémenter
    }

    @Override
    public void exit() {
        // Rien à faire ici pour l’instant
    }
    public Programmeur createProgrammeur(){
        Programmeur programmeur = new Programmeur();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez le nom : ");
        programmeur.setNom(scanner.nextLine());
        System.out.println("Entrez le prénom : ");
        programmeur.setPrenom(scanner.nextLine());
        System.out.println("Entrez l'adresse : ");
        programmeur.setAdresse(scanner.nextLine());
        System.out.println("Entrez le pseudo : ");
        programmeur.setPseudo(scanner.nextLine());
        System.out.println("Entrez le responsable : ");
        programmeur.setResponsable(scanner.nextLine());
        System.out.println("Entrez le hobby : ");
        programmeur.setHobby(scanner.nextLine());
        System.out.println("Entrez l'année de naissance : ");
        programmeur.setNaissance(scanner.nextInt());
        System.out.println("Entrez le salaire : ");
        programmeur.setSalaire(scanner.nextDouble());
        System.out.println("Entrez la prime : ");
        programmeur.setPrime(scanner.nextDouble());
        return programmeur;
    }

}

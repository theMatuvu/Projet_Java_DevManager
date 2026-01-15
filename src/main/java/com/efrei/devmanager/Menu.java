package com.efrei.devmanager;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;
@Component

public class Menu implements CommandLineRunner {

    private final ActionsBDDImpl actionsBDDImpl;

    public Menu(ActionsBDDImpl actionsBDDImpl) {
        this.actionsBDDImpl = actionsBDDImpl;
    }

    @Override
    public void run(String... args) {
        int choix = 0;
        do {
            afficherMenu();
            choix = lireChoix();
            traiterChoix(choix);
        } while (choix != 8);
    }
    private void afficherMenu() {
        System.out.println("********* MENU *************\n" +
                "1. Afficher tous les programmeurs\n" +
                "2. Afficher un programmeur\n" +
                "3. Supprimer un programmeur\n" +
                "4. Ajouter un programmeur\n" +
                "5. Modifier le salaire\n" +
                "6. Afficher la liste des projets (Intitulé, Date de début, Date de fin prévue, Etat, …)\n" +
                "7. Obtenir la liste des programmeurs qui travaillent sur le même projet\n" +
                "8. Quitter le programme\n" +
                "Quel est votre choix ?");
    }

    private int lireChoix() {
        // Code pour lire le choix de l'utilisateur
        Scanner scanner = new Scanner(System.in);
        int choix = scanner.nextInt();
        while (choix < 1 || choix > 8 ) {
            System.out.println("Choix invalide. Veuillez réessayer.");
            choix = scanner.nextInt();
        }

        return choix;
    }

    private void traiterChoix(int choix) {
        switch (choix) {
            case 1:
                actionsBDDImpl.getProgrammeurs().forEach(programmeur -> {
                    System.out.println(programmeur);
                });
                break;
            case 2:
                // Code pour afficher un programmeur
                Scanner scanner = new Scanner(System.in);
                System.out.println("Entrez l'ID du programmeur : ");
                int id = scanner.nextInt();
                Programmeur programmeur = actionsBDDImpl.getProgrammeur(id);
                System.out.println(programmeur);
                break;
            case 3:
                // Code pour supprimer un programmeur
                break;
            case 4:
                // Code pour ajouter un programmeur

                actionsBDDImpl.insertProgrammeur(createProgrammeur());
                break;
            case 5:
                // Code pour modifier le salaire
                break;
            case 6:
                // Code pour afficher la liste des projets
                break;
            case 7:
                // Code pour obtenir la liste des programmeurs sur le même projet
                break;
            case 8:
                // Code pour quitter le programme
                break;
            default:
                System.out.println("Choix invalide. Veuillez réessayer.");
        }
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

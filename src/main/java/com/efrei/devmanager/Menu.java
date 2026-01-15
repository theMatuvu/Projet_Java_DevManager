package com.efrei.devmanager;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;
@Component

public class Menu implements CommandLineRunner {

    private final InterfaceChoix choixImpl;

    public Menu(InterfaceChoix choixImpl) {
        this.choixImpl = choixImpl;
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
        Scanner scanner = new Scanner(System.in);

        switch (choix) {
            case 1:
                choixImpl.printProgrammeurs();
            case 2:
                // Code pour afficher un programmeur
                choixImpl.printProgrammeur();
            case 3:
                // Code pour supprimer un programmeur
               choixImpl.deleteProgrammeur();
                break;
            case 4:
                // Code pour ajouter un programmeur
                choixImpl.addProgrammeur();
                break;
            case 5:
                // Code pour modifier le salaire
                choixImpl.updateProgrammeurSalaire();
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


}

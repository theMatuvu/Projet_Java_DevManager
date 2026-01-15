package com.efrei.devmanager;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Menu {

    private final InterfaceChoix choixImpl;

    public Menu(@Qualifier("consoleImplementation") InterfaceChoix choixImpl) {
        this.choixImpl = choixImpl;
    }

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
        ConsoleImplementation consoleImpl = (ConsoleImplementation) choixImpl;

        switch (choix) {
            case 1:
                consoleImpl.printProgrammeurs();
                break;
            case 2:
                consoleImpl.printProgrammeur();
                break;
            case 3:
                consoleImpl.deleteProgrammeurFromConsole();
                break;
            case 4:
                consoleImpl.addProgrammeurFromConsole();
                break;
            case 5:
                consoleImpl.updateProgrammeurSalaireFromConsole();
                break;
            case 6:
                consoleImpl.printProjectList();
                break;
            case 7:
                consoleImpl.programmerListByProject();
                break;
            case 8:
                System.out.println("Au revoir !");
                break;
            default:
                System.out.println("Choix invalide. Veuillez réessayer.");
        }
    }


}

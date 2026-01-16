package com.efrei.devmanager;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Menu implements CommandLineRunner {

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
        Scanner scanner = new Scanner(System.in);
        int choix = 0;
        boolean validInput = false;
        
        while (!validInput) {
            try {
                choix = scanner.nextInt();
                if (choix < 1 || choix > 8) {
                    System.out.println("Choix invalide. Veuillez choisir un nombre entre 1 et 8.");
                } else {
                    validInput = true;
                }
            } catch (java.util.InputMismatchException e) {
                System.err.println("Erreur : Veuillez saisir un nombre entier.");
                scanner.nextLine(); // Consommer l'entrée invalide
                System.out.println("Quel est votre choix ?");
            }
        }
        
        return choix;
    }

    private void traiterChoix(int choix) {
        ConsoleImplementation consoleImpl = (ConsoleImplementation) choixImpl;

        try {
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
        } catch (java.util.InputMismatchException e) {
            System.err.println("Erreur : Entrée invalide. Veuillez saisir une valeur correcte.");
        } catch (java.time.format.DateTimeParseException e) {
            System.err.println("Erreur : Format de date invalide. Utilisez le format YYYY-MM-DD.");
        } catch (IllegalArgumentException e) {
            System.err.println("Erreur : " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erreur inattendue : " + e.getMessage());
            e.printStackTrace();
        }
    }


}

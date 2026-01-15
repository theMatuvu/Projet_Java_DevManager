package com.efrei.devmanager;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

@Service("consoleImplementation")
public class ConsoleImplementation implements InterfaceChoix {
    private final ActionsBDD actionsBDD;

    public ConsoleImplementation(ActionsBDD actionsBDD) {
        this.actionsBDD = actionsBDD;
    }

    // ========== Méthodes Programmeur ==========
    
    @Override
    public List<Programmeur> getAllProgrammeurs() {
        List<Programmeur> programmeurs = actionsBDD.getProgrammeurs();
        programmeurs.forEach(System.out::println);
        return programmeurs;
    }

    @Override
    public Programmeur getProgrammeurById(int id) {
        Programmeur programmeur = actionsBDD.getProgrammeur(id);
        if (programmeur != null) {
            System.out.println(programmeur);
        } else {
            System.out.println("Programmeur non trouvé.");
        }
        return programmeur;
    }

    @Override
    public void deleteProgrammeur(int id) {
        actionsBDD.deleteProgrammeur(id);
        System.out.println("Programmeur supprimé avec succès.");
    }

    @Override
    public void addProgrammeur(Programmeur programmeur) {
        actionsBDD.insertProgrammeur(programmeur);
        System.out.println("Programmeur ajouté avec succès.");
    }

    @Override
    public void updateProgrammeurSalaire(int id, double salaire) {
        actionsBDD.updateProgrammeurSalaire(id, salaire);
        System.out.println("Salaire mis à jour avec succès.");
    }
    
    // ========== Méthodes Projet ==========
    
    @Override
    public List<Projet> getAllProjets() {
        List<Projet> projets = actionsBDD.getProjets();
        projets.forEach(p -> System.out.println("ID: " + p.getId() + 
                " | Intitulé: " + p.getIntitule() + 
                " | État: " + p.getEtat() + 
                " | Date début: " + p.getDateDebut()));
        return projets;
    }

    @Override
    public Projet getProjetById(int id) {
        Projet projet = actionsBDD.getProjet(id);
        if (projet != null) {
            System.out.println("ID: " + projet.getId() + 
                    " | Intitulé: " + projet.getIntitule() + 
                    " | État: " + projet.getEtat() + 
                    " | Date début: " + projet.getDateDebut() +
                    " | Date fin prévue: " + projet.getDateFinPrevue());
        } else {
            System.out.println("Projet non trouvé.");
        }
        return projet;
    }

    @Override
    public void deleteProjet(int id) {
        actionsBDD.deleteProjet(id);
        System.out.println("Projet supprimé avec succès.");
    }

    @Override
    public void addProjet(Projet projet) {
        actionsBDD.insertProjet(projet);
        System.out.println("Projet ajouté avec succès.");
    }

    @Override
    public void updateProjet(Projet projet) {
        actionsBDD.updateProjet(projet);
        System.out.println("Projet mis à jour avec succès.");
    }
    
    // ========== Méthodes relation Programmeur-Projet ==========
    
    @Override
    public void assignProgrammeurToProjet(int programmeurId, int projetId) {
        actionsBDD.assignProgrammeurToProjet(programmeurId, projetId);
        System.out.println("Programmeur assigné au projet avec succès.");
    }

    @Override
    public void removeProgrammeurFromProjet(int programmeurId, int projetId) {
        actionsBDD.removeProgrammeurFromProjet(programmeurId, projetId);
        System.out.println("Programmeur retiré du projet avec succès.");
    }

    @Override
    public List<Programmeur> getProgrammeursByProjet(int projetId) {
        List<Programmeur> programmeurs = actionsBDD.getProgrammeursByProjet(projetId);
        System.out.println("=== Programmeurs travaillant sur le projet ===");
        programmeurs.forEach(System.out::println);
        return programmeurs;
    }

    @Override
    public List<Projet> getProjetsByProgrammeur(int programmeurId) {
        List<Projet> projets = actionsBDD.getProjetsByProgrammeur(programmeurId);
        System.out.println("=== Projets du programmeur ===");
        projets.forEach(p -> System.out.println("- " + p.getIntitule() + " (" + p.getEtat() + ")"));
        return projets;
    }
    
    // ========== Méthodes utilitaires pour le menu console ==========
    
    public void printProgrammeurs() {
        getAllProgrammeurs();
    }

    public void printProgrammeur() {
        System.out.println("Entrez l'ID du programmeur : ");
        int id = new Scanner(System.in).nextInt();
        getProgrammeurById(id);
    }

    public void deleteProgrammeurFromConsole() {
        System.out.println("Entrez l'ID du programmeur à supprimer : ");
        int id = new Scanner(System.in).nextInt();
        deleteProgrammeur(id);
    }

    public void addProgrammeurFromConsole() {
        Programmeur programmeur = createProgrammeur();
        addProgrammeur(programmeur);
    }

    public void updateProgrammeurSalaireFromConsole() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez l'ID du programmeur : ");
        int id = scanner.nextInt();
        System.out.println("Entrez le nouveau salaire : ");
        double salaire = scanner.nextDouble();
        updateProgrammeurSalaire(id, salaire);
    }

    public void printProjectList() {
        getAllProjets();
    }

    public void programmerListByProject() {
        System.out.println("Entrez l'ID du projet : ");
        int projetId = new Scanner(System.in).nextInt();
        getProgrammeursByProjet(projetId);
    }

    public Programmeur createProgrammeur() {
        Programmeur programmeur = new Programmeur();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez l'ID : ");
        programmeur.setId(scanner.nextInt());
        scanner.nextLine(); // Consommer le retour à la ligne
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

    public Projet createProjet() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez l'intitulé du projet : ");
        String intitule = scanner.nextLine();
        System.out.println("Entrez la date de début (YYYY-MM-DD) : ");
        String dateDebutStr = scanner.nextLine();
        System.out.println("Entrez la date de fin prévue (YYYY-MM-DD) : ");
        String dateFinStr = scanner.nextLine();
        System.out.println("Entrez l'état (ex: En cours, Terminé, Planifié) : ");
        String etat = scanner.nextLine();
        
        LocalDate dateDebut = LocalDate.parse(dateDebutStr);
        LocalDate dateFinPrevue = LocalDate.parse(dateFinStr);
        
        return new Projet(intitule, dateDebut, dateFinPrevue, etat);
    }
}

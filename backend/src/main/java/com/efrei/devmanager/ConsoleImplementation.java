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

    // Gestion des programmeurs
    
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
    
    // Gestion des projets
    
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
    
    // Association entre programmeurs et projets
    
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
    
    // Méthodes pour l'interaction console avec l'utilisateur
    
    public void printProgrammeurs() {
        getAllProgrammeurs();
    }

    public void printProgrammeur() {
        try {
            System.out.println("Entrez l'ID du programmeur : ");
            Scanner scanner = new Scanner(System.in);
            int id = scanner.nextInt();
            getProgrammeurById(id);
        } catch (java.util.InputMismatchException e) {
            System.err.println("Erreur : Veuillez saisir un nombre entier valide pour l'ID.");
        } catch (Exception e) {
            System.err.println("Erreur : " + e.getMessage());
        }
    }

    public void deleteProgrammeurFromConsole() {
        try {
            System.out.println("Entrez l'ID du programmeur à supprimer : ");
            Scanner scanner = new Scanner(System.in);
            int id = scanner.nextInt();
            deleteProgrammeur(id);
        } catch (java.util.InputMismatchException e) {
            System.err.println("Erreur : Veuillez saisir un nombre entier valide pour l'ID.");
        } catch (Exception e) {
            System.err.println("Erreur : " + e.getMessage());
        }
    }

    public void addProgrammeurFromConsole() {
        try {
            Programmeur programmeur = createProgrammeur();
            addProgrammeur(programmeur);
        } catch (java.util.InputMismatchException e) {
            System.err.println("Erreur : Saisie invalide. Vérifiez que vous entrez les bons types de données.");
        } catch (Exception e) {
            System.err.println("Erreur lors de l'ajout du programmeur : " + e.getMessage());
        }
    }

    public void updateProgrammeurSalaireFromConsole() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Entrez l'ID du programmeur : ");
            int id = scanner.nextInt();
            System.out.println("Entrez le nouveau salaire : ");
            double salaire = scanner.nextDouble();
            updateProgrammeurSalaire(id, salaire);
        } catch (java.util.InputMismatchException e) {
            System.err.println("Erreur : Saisie invalide. L'ID doit être un nombre entier et le salaire un nombre décimal.");
        } catch (Exception e) {
            System.err.println("Erreur lors de la mise à jour du salaire : " + e.getMessage());
        }
    }

    public void printProjectList() {
        getAllProjets();
    }

    public void programmerListByProject() {
        try {
            System.out.println("Entrez l'ID du projet : ");
            Scanner scanner = new Scanner(System.in);
            int projetId = scanner.nextInt();
            getProgrammeursByProjet(projetId);
        } catch (java.util.InputMismatchException e) {
            System.err.println("Erreur : Veuillez saisir un nombre entier valide pour l'ID du projet.");
        } catch (Exception e) {
            System.err.println("Erreur : " + e.getMessage());
        }
    }

    public Programmeur createProgrammeur() {
        Programmeur programmeur = new Programmeur();
        Scanner scanner = new Scanner(System.in);
        
        try {
            // Validation de l'ID (doit être un entier)
            System.out.println("Entrez l'ID : ");
            if (!scanner.hasNextInt()) {
                scanner.nextLine();
                throw new IllegalArgumentException("L'ID doit être un nombre entier.");
            }
            programmeur.setId(scanner.nextInt());
            scanner.nextLine();
            
            // Validation du nom (doit être une chaîne de caractères)
            System.out.println("Entrez le nom : ");
            String nom = scanner.nextLine().trim();
            if (nom.isEmpty() || nom.matches(".*\\d.*")) {
                throw new IllegalArgumentException("Le nom doit être une chaîne de caractères valide sans chiffres.");
            }
            programmeur.setNom(nom);
            
            // Validation du prénom (doit être une chaîne de caractères)
            System.out.println("Entrez le prénom : ");
            String prenom = scanner.nextLine().trim();
            if (prenom.isEmpty() || prenom.matches(".*\\d.*")) {
                throw new IllegalArgumentException("Le prénom doit être une chaîne de caractères valide sans chiffres.");
            }
            programmeur.setPrenom(prenom);
            
            // Validation de l'adresse (doit être une chaîne de caractères)
            System.out.println("Entrez l'adresse : ");
            String adresse = scanner.nextLine().trim();
            if (adresse.isEmpty()) {
                throw new IllegalArgumentException("L'adresse ne peut pas être vide.");
            }
            programmeur.setAdresse(adresse);
            
            // Validation du pseudo (doit être une chaîne de caractères)
            System.out.println("Entrez le pseudo : ");
            String pseudo = scanner.nextLine().trim();
            if (pseudo.isEmpty()) {
                throw new IllegalArgumentException("Le pseudo ne peut pas être vide.");
            }
            programmeur.setPseudo(pseudo);
            
            // Validation du responsable (doit être une chaîne de caractères)
            System.out.println("Entrez le responsable : ");
            String responsable = scanner.nextLine().trim();
            if (responsable.isEmpty()) {
                throw new IllegalArgumentException("Le responsable ne peut pas être vide.");
            }
            programmeur.setResponsable(responsable);
            
            // Validation du hobby (doit être une chaîne de caractères)
            System.out.println("Entrez le hobby : ");
            String hobby = scanner.nextLine().trim();
            if (hobby.isEmpty()) {
                throw new IllegalArgumentException("Le hobby ne peut pas être vide.");
            }
            programmeur.setHobby(hobby);
            
            // Validation de l'année de naissance (doit être un entier)
            System.out.println("Entrez l'année de naissance : ");
            if (!scanner.hasNextInt()) {
                scanner.nextLine();
                throw new IllegalArgumentException("L'année de naissance doit être un nombre entier.");
            }
            int naissance = scanner.nextInt();
            if (naissance < 1900 || naissance > 2026) {
                throw new IllegalArgumentException("L'année de naissance doit être comprise entre 1900 et 2026.");
            }
            programmeur.setNaissance(naissance);
            
            // Validation du salaire (doit être un nombre décimal)
            System.out.println("Entrez le salaire : ");
            if (!scanner.hasNextDouble()) {
                scanner.nextLine();
                throw new IllegalArgumentException("Le salaire doit être un nombre décimal (ex: 50000.0).");
            }
            double salaire = scanner.nextDouble();
            if (salaire < 0) {
                throw new IllegalArgumentException("Le salaire ne peut pas être négatif.");
            }
            programmeur.setSalaire(salaire);
            
            // Validation de la prime (doit être un nombre décimal)
            System.out.println("Entrez la prime : ");
            if (!scanner.hasNextDouble()) {
                scanner.nextLine();
                throw new IllegalArgumentException("La prime doit être un nombre décimal (ex: 5000.0).");
            }
            double prime = scanner.nextDouble();
            if (prime < 0) {
                throw new IllegalArgumentException("La prime ne peut pas être négative.");
            }
            programmeur.setPrime(prime);
            
        } catch (java.util.InputMismatchException e) {
            scanner.nextLine(); // Nettoyer le buffer
            throw new IllegalArgumentException("Erreur de saisie : Veuillez respecter les types de données demandés.");
        }
        
        return programmeur;
    }

    public Projet createProjet() {
        Scanner scanner = new Scanner(System.in);
        
        try {
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
            
        } catch (java.time.format.DateTimeParseException e) {
            throw new IllegalArgumentException("Erreur : Format de date invalide. Utilisez le format YYYY-MM-DD (ex: 2026-01-16).");
        }
    }
}

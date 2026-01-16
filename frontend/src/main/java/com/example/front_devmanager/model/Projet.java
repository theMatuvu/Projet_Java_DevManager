package com.example.front_devmanager.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Projet {
    private Integer id;
    private String intitule;
    private LocalDate dateDebut;
    private LocalDate dateFinPrevue;
    private String etat;
    private Set<Programmeur> programmeurs = new HashSet<>();

    public Projet() {
    }

    public Projet(String intitule, LocalDate dateDebut, LocalDate dateFinPrevue, String etat) {
        this.intitule = intitule;
        this.dateDebut = dateDebut;
        this.dateFinPrevue = dateFinPrevue;
        this.etat = etat;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFinPrevue() {
        return dateFinPrevue;
    }

    public void setDateFinPrevue(LocalDate dateFinPrevue) {
        this.dateFinPrevue = dateFinPrevue;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Set<Programmeur> getProgrammeurs() {
        return programmeurs;
    }

    public void setProgrammeurs(Set<Programmeur> programmeurs) {
        this.programmeurs = programmeurs;
    }

    @Override
    public String toString() {
        return intitule + " (" + etat + ")";
    }
}

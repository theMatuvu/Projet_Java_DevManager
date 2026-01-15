package com.efrei.devmanager;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Setter;

@Entity
@Table(name = "programmeur")
@Data
@Setter
public class Programmeur {
    @Id
    private int id;
    private String nom;
    private String prenom;
    private String adresse;
    private String pseudo;
    private String responsable;
    private String hobby;
    private int naissance;
    private double  salaire;
    private double prime;

    public Programmeur() {

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public void setNaissance(int naissance) {
        this.naissance = naissance;
    }

    public void setSalaire(double salaire) {
        this.salaire = salaire;
    }

    public void setPrime(double prime) {
        this.prime = prime;
    }

    public String toString(){
        return "Id: " + id + "\n" +
               "Nom: " + nom + "\n" +
               "Prénom: " + prenom + "\n" +
               "Adresse: " + adresse + "\n" +
               "Pseudo: " + pseudo + "\n" +
               "Responsable: " + responsable + "\n" +
               "Hobby: " + hobby + "\n" +
               "Année de naissance: " + naissance + "\n" +
               "Salaire: " + salaire + "\n" +
               "Prime: " + prime + "\n";
    }
}

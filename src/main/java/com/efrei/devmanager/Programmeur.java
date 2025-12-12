package com.efrei.devmanager;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
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

}

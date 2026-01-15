package com.efrei.devmanager;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "projet")
@Data
public class Projet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false)
    private String intitule;
    
    @Column(name = "date_debut")
    private LocalDate dateDebut;
    
    @Column(name = "date_fin_prevue")
    private LocalDate dateFinPrevue;
    
    private String etat;
    
    @ManyToMany
    @JoinTable(
        name = "programmeur_projet",
        joinColumns = @JoinColumn(name = "projet_id"),
        inverseJoinColumns = @JoinColumn(name = "programmeur_id")
    )
    private Set<Programmeur> programmeurs = new HashSet<>();

    public Projet() {}

    public Projet(String intitule, LocalDate dateDebut, LocalDate dateFinPrevue, String etat) {
        this.intitule = intitule;
        this.dateDebut = dateDebut;
        this.dateFinPrevue = dateFinPrevue;
        this.etat = etat;
    }
}

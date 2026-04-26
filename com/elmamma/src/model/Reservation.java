package com.elmamma.src.model;

import java.time.LocalDateTime;

public class Reservation {
    private int id;
    private LocalDateTime dateReservation;
    private Utilisateur etudiant;
    private Plat plat;

    public Reservation() {
        this.dateReservation = LocalDateTime.now();
    }

    public Reservation(Utilisateur etudiant, Plat plat) {
        this();
        this.etudiant = etudiant;
        this.plat = plat;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public LocalDateTime getDateReservation() { return dateReservation; }
    public void setDateReservation(LocalDateTime dateReservation) { this.dateReservation = dateReservation; }
    public Utilisateur getEtudiant() { return etudiant; }
    public void setEtudiant(Utilisateur etudiant) { this.etudiant = etudiant; }
    public Plat getPlat() { return plat; }
    public void setPlat(Plat plat) { this.plat = plat; }
}

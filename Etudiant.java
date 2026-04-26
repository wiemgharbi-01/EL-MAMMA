package com.elmamma.models;

public class Etudiant {
    private String nom;
    private double solde;
    private boolean consigneRendue;

    // Constructeur vide
    public Etudiant() {}

    // Constructeur complet
    public Etudiant(String nom, double solde, boolean consigneRendue) {
        this.nom = nom;
        this.solde = solde;
        this.consigneRendue = consigneRendue;
    }

    // Méthode métier
    public void rechargerSolde(double montant) {
        if (montant > 0) {
            this.solde += montant;
        }
    }

    // Getters / Setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public boolean isConsigneRendue() {
        return consigneRendue;
    }

    public void setConsigneRendue(boolean consigneRendue) {
        this.consigneRendue = consigneRendue;
    }

    @Override
    public String toString() {
        return "Etudiant{" +
                "nom='" + nom + '\'' +
                ", solde=" + solde +
                ", consigneRendue=" + consigneRendue +
                '}';
    }
}
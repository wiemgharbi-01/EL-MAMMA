package com.elmamma;

public class Etudiant {
    private String nom;
    private double solde;
    private boolean consigneRendue;

    public Etudiant(String nom, double solde) {
        this.nom = nom;
        this.solde = solde;
    }

    public Etudiant(String nom, double solde, boolean consigneRendue) {
        this.nom = nom;
        this.solde = solde;
        this.consigneRendue = consigneRendue;
    }

    public String getNom() { return nom; }
    public double getSolde() { return solde; }
    public void setSolde(double solde) { this.solde = solde; }
    public boolean isConsigneRendue() { return consigneRendue; }
    public void setConsigneRendue(boolean consigneRendue) { this.consigneRendue = consigneRendue; }
}
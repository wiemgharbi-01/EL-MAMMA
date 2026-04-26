package com.elmamma.models;

public class Commande {
    private int id;
    private double prix;
    private Etudiant etudiant;

    public Commande() {}

    public Commande(int id, double prix, Etudiant etudiant) {
        this.id = id;
        this.prix = prix;
        this.etudiant = etudiant;
    }

    // 🔥 LOGIQUE MÉTIER CORRIGÉE
    public double calculerPrixFinal() {
        if (prix <= 0) {
            throw new IllegalArgumentException("Prix invalide");
        }

        double prixFinal = prix;

        if (etudiant.isConsigneRendue()) {
            prixFinal = prixFinal * 0.9; // ✔ correction ici
            etudiant.setConsigneRendue(false);
        }

        return prixFinal;
    }

    // 💳 PAIEMENT PROPRE
    public boolean payer() {
        double prixFinal = calculerPrixFinal();

        if (etudiant.getSolde() >= prixFinal) {
            etudiant.setSolde(etudiant.getSolde() - prixFinal);
            System.out.println("Paiement de " + prixFinal + " DT effectué");
            return true;
        } else {
            System.out.println("Solde insuffisant !");
            return false;
        }
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public double getPrix() { return prix; }
    public void setPrix(double prix) { this.prix = prix; }

    public Etudiant getEtudiant() { return etudiant; }
    public void setEtudiant(Etudiant etudiant) { this.etudiant = etudiant; }
}
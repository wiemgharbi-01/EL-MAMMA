package com.elmamma;

public class Commande {
    private double prixInitial;
    private Etudiant etudiant;
    private Plat platSelectionne;

    public Commande() {}
    public Commande(int id, double prix, Etudiant etudiant) {
        this.prixInitial = prix;
        this.etudiant = etudiant;
    }

    public void reserverPlat(Plat p) {
        this.platSelectionne = p;
        this.prixInitial = p.getPrix();
        p.reserverPlat();
    }

    public double calculerPrixFinal() {
        return etudiant.isConsigneRendue() ? prixInitial * 0.9 : prixInitial;
    }

    public boolean payer() {
        double total = calculerPrixFinal();
        if (etudiant.getSolde() >= total) {
            etudiant.setSolde(etudiant.getSolde() - total);
            etudiant.setConsigneRendue(false); // Reset discount after use
            System.out.println("   > [PAYMENT] Paid: " + total + " DT (New Balance: " + etudiant.getSolde() + " DT)");
            return true;
        }
        System.out.println("   > [PAYMENT] Error: Insufficient funds!");
        return false;
    }
}
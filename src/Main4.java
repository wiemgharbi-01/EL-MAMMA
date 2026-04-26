package com.elmamma.models;

public class Main {
    public static void main(String[] args) {

        System.out.println("=== El-Mamma — Test Consigne & Remise ===");

        Etudiant e1 = new Etudiant("Fatma", 20.0, true);

        Commande c1 = new Commande(1, 10.0, e1);

        double prixFinal = c1.calculerPrixFinal();
        System.out.println("Prix après remise : " + prixFinal + " DT");

        boolean paye = c1.payer();

        System.out.println("Paiement réussi ? " + paye);

        System.out.println("Solde restant : " + e1.getSolde() + " DT");
        System.out.println("Consigne utilisée : " + e1.isConsigneRendue());
    }
}
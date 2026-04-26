package com.elmamma;

public class Paiement {
    public void payer(Etudiant e, Commande c) {
        if (c.payer()) System.out.println("   > [SYSTEM] Transaction successful for " + e.getNom());
    }
}
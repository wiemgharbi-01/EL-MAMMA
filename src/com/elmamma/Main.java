package com.elmamma;

public class Main {
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("        EL-MAMMA MASTER SYSTEM         ");
        System.out.println("========================================\n");

        // --- TEST 1: Stock Management ---
        System.out.println("[SCENARIO 1: STOCK CHECK]");
        Plat pasta = new Plat("Pasta", 2);
        pasta.reserverPlat();
        pasta.reserverPlat();
        pasta.reserverPlat(); // Should fail

        // --- TEST 2: Full Journey (Ali) ---
        System.out.println("\n[SCENARIO 2: COMPLETE ORDER FLOW]");
        Etudiant ali = new Etudiant("Ali", 20.0);
        Menu menu = new Menu();
        Plat p1 = new Plat("Couscous", 12.0, 5);
        menu.ajouterPlat(p1);
        menu.afficherMenu();

        Commande cmdAli = new Commande();
        cmdAli.reserverPlat(p1);
        new Paiement().payer(ali, cmdAli);
        new Cuisiniere().changerStatut();

        // --- TEST 3: Eco-Remise (Fatma) ---
        System.out.println("\n[SCENARIO 3: ECO-DISCOUNT CHECK]");
        Etudiant fatma = new Etudiant("Fatma", 15.0, true); // Has returned container
        System.out.println("Fatma initial balance: " + fatma.getSolde() + " DT");
        
        Commande cmdFatma = new Commande(1, 10.0, fatma); // 10 DT Base Price
        System.out.println("Base Price: 10.0 DT | Price with Eco-Discount: " + cmdFatma.calculerPrixFinal() + " DT");
        cmdFatma.payer();

        System.out.println("\n========================================");
        System.out.println("        END OF SYSTEM DEMO             ");
        System.out.println("========================================");
    }
}
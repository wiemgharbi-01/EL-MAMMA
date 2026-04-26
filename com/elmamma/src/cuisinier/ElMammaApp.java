package com.elmamma.src.cuisinier;

import com.elmamma.src.db.*;
import com.elmamma.src.model.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ElMammaApp {

    private static final List<Commande> commandes = new ArrayList<>();

    private static final StockDAO       stockDAO       = new StockDAO();
    private static final UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
    private static final PlatDAO        platDAO        = new PlatDAO();
    private static final ReservationDAO reservationDAO = new ReservationDAO();
    private static final CommandeDAO    commandeDAO    = new CommandeDAO();

    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║           EL-MAMMA - GESTION DES COMMANDES                 ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");

        try {
            // --- Setup data ---
            Stock stockBurger = stockDAO.insert(new Stock(0, 50));
            Stock stockSalade = stockDAO.insert(new Stock(0, 20));
            Stock stockSteak  = stockDAO.insert(new Stock(0, 15));

            Plat burger = platDAO.insert(new Plat(0, "Burger Classique", 450.0, stockBurger));
            Plat salade = platDAO.insert(new Plat(0, "Salade César",     350.0, stockSalade));
            Plat steak  = platDAO.insert(new Plat(0, "Steak Frites",     600.0, stockSteak));

            Utilisateur etudiant1 = utilisateurDAO.insertOrUpdate(new Utilisateur(101, "Ali Ben", "ali@univ.dz"));
            Utilisateur etudiant2 = utilisateurDAO.insertOrUpdate(new Utilisateur(102, "Sara K.", "sara@univ.dz"));

            // --- Reservations ---
            System.out.println("📱 [UI Étudiant] Réservation en cours...\n");
            reserverPlat(etudiant1, burger, 2, "Sans oignons, sauce à part");
            reserverPlat(etudiant2, salade, 1, "Vinaigrette à part");
            reserverPlat(etudiant1, steak,  1, "À point, frites croustillantes");

            // --- Dashboard ---
            System.out.println("\n══════════════════════════════════════════════════════════════");
            System.out.println("🖥️  [UI Personnel] TABLEAU DE BORD");
            System.out.println("══════════════════════════════════════════════════════════════");
            afficherTableauDeBord();

            // --- Status changes ---
            System.out.println("\n🔄 [Personnel] Déplacement des commandes...\n");
            Commande cmd1 = commandes.get(0);
            Commande cmd2 = commandes.get(1);
            Commande cmd3 = commandes.get(2);

            changerStatutEtSauvegarder(cmd1, StatutCommande.EN_COURS);
            changerStatutEtSauvegarder(cmd2, StatutCommande.EN_COURS);
            changerStatutEtSauvegarder(cmd3, StatutCommande.PRET);
            changerStatutEtSauvegarder(cmd1, StatutCommande.PRET);

            System.out.println("\n══════════════════════════════════════════════════════════════");
            System.out.println("🖥️  [UI Personnel] TABLEAU DE BORD - MISE À JOUR");
            System.out.println("══════════════════════════════════════════════════════════════");
            afficherTableauDeBord();

            System.out.println("\n✅ Démo terminée.");

        } catch (SQLException e) {
            System.err.println("❌ Erreur base de données : " + e.getMessage());
            e.printStackTrace();
        } finally {
            DatabaseManager.closeConnection();
        }
    }

    private static void reserverPlat(Utilisateur etudiant, Plat plat,
                                     int quantite, String details) throws SQLException {
        System.out.println("────────────────────────────────────────");
        System.out.println("👤 Étudiant : " + etudiant.getNom());
        System.out.println("🍽️  Plat     : " + plat.getNom() + " (x" + quantite + ")");

        if (!plat.verifierDisponibilite()) {
            System.out.println("❌ Échec : stock épuisé !\n");
            return;
        }
        boolean ok = plat.getStock().decrementer(quantite);
        if (!ok) {
            System.out.println("❌ Échec : stock insuffisant !\n");
            return;
        }

        // Persist stock change
        stockDAO.updateQuantite(plat.getStock());

        // Persist reservation
        Reservation reservation = new Reservation(etudiant, plat);
        reservationDAO.insert(reservation);

        // Persist commande
        Commande commande = new Commande(reservation, plat, etudiant, quantite, details);
        commandeDAO.insert(commande);
        commandes.add(commande);

        System.out.println("✅ Réservation confirmée !");
        System.out.println("   Commande " + commande.getReference() + " créée. (DB id=" + commande.getId() + ")");
        System.out.println("   Statut   : " + commande.getStatut());
        System.out.println("   Prête à  : " + commande.getHeurePrete().toLocalTime() + "\n");
    }

    private static void changerStatutEtSauvegarder(Commande c, StatutCommande statut) throws SQLException {
        c.changerStatut(statut);
        commandeDAO.updateStatut(c);
        System.out.println("   ➤ " + c.getReference() + " passée en \"" + statut + "\" (sauvegardé en DB)");
    }

    private static void afficherTableauDeBord() {
        List<Commande> commandesList = new ArrayList<>();
        List<Commande> enCoursList   = new ArrayList<>();
        List<Commande> pretList      = new ArrayList<>();

        for (Commande c : commandes) {
            switch (c.getStatut()) {
                case COMMANDE -> commandesList.add(c);
                case EN_COURS -> enCoursList.add(c);
                case PRET     -> pretList.add(c);
            }
        }

        System.out.println("┌─────────────────────┬─────────────────────┬─────────────────────┐");
        System.out.println("│   📋 COMMANDÉ (" + commandesList.size() + ")    │   🔥 EN COURS (" + enCoursList.size() + ")   │   ✅ PRÊT (" + pretList.size() + ")      │");
        System.out.println("├─────────────────────┼─────────────────────┼─────────────────────┤");
        int max = Math.max(commandesList.size(), Math.max(enCoursList.size(), pretList.size()));
        for (int i = 0; i < max; i++) {
            String col1 = i < commandesList.size() ? truncate(commandesList.get(i).toCardString(), 19) : "";
            String col2 = i < enCoursList.size()   ? truncate(enCoursList.get(i).toCardString(), 19)   : "";
            String col3 = i < pretList.size()      ? truncate(pretList.get(i).toCardString(), 19)      : "";
            System.out.printf("│ %-19s │ %-19s │ %-19s │%n", col1, col2, col3);
        }
        System.out.println("└─────────────────────┴─────────────────────┴─────────────────────┘");
    }

    private static String truncate(String s, int len) {
        if (s.length() <= len) return s;
        return s.substring(0, len - 3) + "...";
    }
}

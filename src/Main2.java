public class Main {
    public static void main(String[] args) {

        System.out.println("=== EL-MAMMA DEMO ===");

        Etudiant e1 = new Etudiant("Ali", 20);
        Menu menu = new Menu();

        Plat p1 = new Plat("Pâtes", 8.0, 10);
        Plat p2 = new Plat("Couscous", 12.0, 5);

        menu.ajouterPlat(p1);
        menu.ajouterPlat(p2);

        menu.afficherMenu();

        Commande c1 = new Commande();

        System.out.println("\n--- Réservation ---");
        c1.reserverPlat(p1);

        System.out.println("\n--- Paiement ---");
        Paiement pay = new Paiement();
        pay.payer(e1, c1);

        System.out.println("\n--- Cuisine ---");
        Cuisiniere cu = new Cuisiniere();
        cu.changerStatutCommande();

        System.out.println("\nFIN DEMO");
    }
}
public class plat {
    private String nom;
    private int stock;

    public plat(String nom, int stock) {
        this.nom = nom;
        this.stock = stock;
    }

    public synchronized boolean reserverPlat() {
        if (stock > 0) {
            stock--;
            System.out.println("Réservation confirmée pour : " + nom);
            System.out.println("Stock restant : " + stock);
            return true;
        } else {
            System.out.println("Stock épuisé pour : " + nom);
            return false;
        }
    }

    public int getStock() {
        return stock;
    }

    public String getNom() {
        return nom;
    }
}
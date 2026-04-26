public class Portefeuille {
    private double solde;

    public Portefeuille(double soldeInitial) {
        this.solde = soldeInitial;
    }

    public double getSolde() {
        return solde;
    }

    public void ajouterArgent(double montant) {
        this.solde += montant;
    }

    public boolean payer(Plat plat) {
        if (this.solde >= plat.getPrix()) {
            this.solde -= plat.getPrix();
            return true;
        }
        return false;
    }
}
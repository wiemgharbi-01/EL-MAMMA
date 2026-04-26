public class Session {
    private String emailUtilisateur;
    private Portefeuille portefeuille;
    private boolean estConnecte;

    public Session() {
        this.estConnecte = false;
    }

    public void connecter(String email, double soldeInitial) {
        this.emailUtilisateur = email;
        this.portefeuille = new Portefeuille(soldeInitial);
        this.estConnecte = true;
    }

    public void deconnecter() {
        this.emailUtilisateur = null;
        this.portefeuille = null;
        this.estConnecte = false;
    }

    public String getEmail() { return emailUtilisateur; }
    public Portefeuille getPortefeuille() { return portefeuille; }
    public boolean estConnecte() { return estConnecte; }
}
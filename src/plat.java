public class Plat {
    private int id;
    private String nom;
    private String description;
    private double prix;
    private int tempsPrep;
    private double note;

    public Plat(int id, String nom, String description, double prix, int tempsPrep, double note) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.tempsPrep = tempsPrep;
        this.note = note;
    }

    public int getId() { return id; }
    public String getNom() { return nom; }
    public String getDescription() { return description; }
    public double getPrix() { return prix; }
    public int getTempsPrep() { return tempsPrep; }
    public double getNote() { return note; }

    public String toString() {
        return nom + " - " + prix + " DT";
    }
}
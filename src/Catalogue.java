import java.util.ArrayList;
import java.util.List;

public class Catalogue {
    private List<Plat> listePlats;

    public Catalogue() {
        this.listePlats = new ArrayList<>();
    }

    public void ajouterPlat(Plat p) {
        listePlats.add(p);
    }

    public List<Plat> filtrerPrix(double prixMax) {
        List<Plat> result = new ArrayList<>();
        for (Plat p : listePlats) {
            if (p.getPrix() <= prixMax) {
                result.add(p);
            }
        }
        return result;
    }

    public List<Plat> filtrerNote(double noteMin) {
        List<Plat> result = new ArrayList<>();
        for (Plat p : listePlats) {
            if (p.getNote() >= noteMin) {
                result.add(p);
            }
        }
        return result;
    }

    public List<Plat> chercher(String texte) {
        List<Plat> result = new ArrayList<>();
        for (Plat p : listePlats) {
            if (p.getNom().toLowerCase().contains(texte.toLowerCase())) {
                result.add(p);
            }
        }
        return result;
    }

    public List<Plat> getListePlats() {
        return listePlats;
    }
}
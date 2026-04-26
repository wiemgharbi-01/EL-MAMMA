package com.elmamma;
import java.util.ArrayList;
import java.util.List;

public class Catalogue {
    private List<Plat> listePlats = new ArrayList<>();

    public void ajouterPlat(Plat p) { listePlats.add(p); }

    public List<Plat> chercher(String texte) {
        List<Plat> result = new ArrayList<>();
        for (Plat p : listePlats) {
            if (p.getNom().toLowerCase().contains(texte.toLowerCase())) result.add(p);
        }
        return result;
    }
}
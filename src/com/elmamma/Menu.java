package com.elmamma;
import java.util.ArrayList;
import java.util.List;

public class Menu {
    private List<Plat> plats = new ArrayList<>();
    public void ajouterPlat(Plat p) { plats.add(p); }
    public void afficherMenu() {
        System.out.println("\n--- TODAY'S MENU ---");
        for (Plat p : plats) System.out.println(" - " + p.getNom() + " | " + p.getPrix() + " DT");
    }
}
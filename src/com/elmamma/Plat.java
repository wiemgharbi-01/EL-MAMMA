package com.elmamma;

public class Plat {
    private int id;
    private String nom;
    private String description;
    private double prix;
    private int stock;
    private double note;

    // Flexible constructors for your different test cases
    public Plat(String nom, int stock) {
        this.nom = nom;
        this.stock = stock;
    }

    public Plat(String nom, double prix, int stock) {
        this.nom = nom;
        this.prix = prix;
        this.stock = stock;
    }

    public Plat(int id, String nom, String description, double prix, int stock, double note) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.stock = stock;
        this.note = note;
    }

    public synchronized boolean reserverPlat() {
        if (stock > 0) {
            stock--;
            System.out.println("   > [STOCK] Reserved: " + nom + " (Remaining: " + stock + ")");
            return true;
        }
        System.out.println("   > [STOCK] Error: " + nom + " is out of stock!");
        return false;
    }

    public String getNom() { return nom; }
    public double getPrix() { return prix; }
    public int getStock() { return stock; }
    @Override
    public String toString() { return nom + " (" + prix + " DT)"; }
}
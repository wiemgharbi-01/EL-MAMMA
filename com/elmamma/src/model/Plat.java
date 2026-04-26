package com.elmamma.src.model;

public class Plat {
    private int id;
    private String nom;
    private double prix;
    private Stock stock;

    public Plat() {}

    public Plat(int id, String nom, double prix, Stock stock) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.stock = stock;
    }

    public boolean verifierDisponibilite() {
        return stock != null && stock.getQuantite() > 0;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public double getPrix() { return prix; }
    public void setPrix(double prix) { this.prix = prix; }
    public Stock getStock() { return stock; }
    public void setStock(Stock stock) { this.stock = stock; }
}

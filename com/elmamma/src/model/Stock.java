package com.elmamma.src.model;

public class Stock {
    private int id;
    private int quantite;

    public Stock() {}

    public Stock(int id, int quantite) {
        this.id = id;
        this.quantite = quantite;
    }

    public boolean decrementer(int qte) {
        if (quantite >= qte) {
            quantite -= qte;
            return true;
        }
        return false;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getQuantite() { return quantite; }
    public void setQuantite(int quantite) { this.quantite = quantite; }
}

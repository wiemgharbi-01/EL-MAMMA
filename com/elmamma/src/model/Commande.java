package com.elmamma.src.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

public class Commande {
    private static final AtomicInteger COMPTEUR = new AtomicInteger(1242);
    private static final DateTimeFormatter TIME_FMT = DateTimeFormatter.ofPattern("HH:mm");

    private int id;
    private String nomRepas;
    private String numeroClient;
    private int quantite;
    private String details;
    private StatutCommande statut;
    private LocalDateTime heurePrete;
    private String reference;

    private Plat plat;
    private Utilisateur utilisateur;
    private Reservation reservation;

    public Commande() {}

    public Commande(Reservation reservation, Plat plat, Utilisateur utilisateur,
                    int quantite, String details) {
        this.id = COMPTEUR.incrementAndGet();
        this.reservation = reservation;
        this.plat = plat;
        this.utilisateur = utilisateur;
        this.quantite = quantite;
        this.details = details;
        this.nomRepas = (plat != null) ? plat.getNom() : "";
        this.numeroClient = (utilisateur != null) ? String.valueOf(utilisateur.getId()) : "";
        this.statut = StatutCommande.COMMANDE;
        this.heurePrete = calculerHeurePrete();
        this.reference = genererReference();
    }

    public LocalDateTime calculerHeurePrete() {
        return LocalDateTime.now().plusMinutes(25);
    }

    public void changerStatut(StatutCommande nouveau) {
        this.statut = nouveau;
    }

    private String genererReference() {
        return "#C-" + id;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNomRepas() { return nomRepas; }
    public void setNomRepas(String nomRepas) { this.nomRepas = nomRepas; }
    public String getNumeroClient() { return numeroClient; }
    public void setNumeroClient(String numeroClient) { this.numeroClient = numeroClient; }
    public int getQuantite() { return quantite; }
    public void setQuantite(int quantite) { this.quantite = quantite; }
    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }
    public StatutCommande getStatut() { return statut; }
    public void setStatut(StatutCommande statut) { this.statut = statut; }
    public LocalDateTime getHeurePrete() { return heurePrete; }
    public void setHeurePrete(LocalDateTime heurePrete) { this.heurePrete = heurePrete; }
    public String getReference() { return reference; }
    public void setReference(String reference) { this.reference = reference; }
    public Plat getPlat() { return plat; }
    public void setPlat(Plat plat) { this.plat = plat; }
    public Utilisateur getUtilisateur() { return utilisateur; }
    public void setUtilisateur(Utilisateur utilisateur) { this.utilisateur = utilisateur; }
    public Reservation getReservation() { return reservation; }
    public void setReservation(Reservation reservation) { this.reservation = reservation; }

    public String toCardString() {
        return String.format(
                "%-18s | %-10s | %dx plat • %s | Prêt à %s | %s",
                nomRepas, statut, quantite, reference,
                heurePrete.format(TIME_FMT),
                (details != null && !details.isEmpty()) ? "📝 " + details : ""
        );
    }

    @Override
    public String toString() {
        return toCardString();
    }
}

package com.elmamma.src.db;

import com.elmamma.src.model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommandeDAO {

    public Commande insert(Commande c) throws SQLException {
        String sql = "INSERT INTO commandes " +
                "(reference, nom_repas, numero_client, quantite, details, statut, heure_prete, reservation_id, utilisateur_id, plat_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = DatabaseManager.getConnection()
                .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, c.getReference());
            ps.setString(2, c.getNomRepas());
            ps.setString(3, c.getNumeroClient());
            ps.setInt(4, c.getQuantite());
            ps.setString(5, c.getDetails());
            ps.setString(6, c.getStatut().name());
            ps.setTimestamp(7, Timestamp.valueOf(c.getHeurePrete()));
            ps.setInt(8, c.getReservation() != null ? c.getReservation().getId() : 0);
            ps.setInt(9, c.getUtilisateur() != null ? c.getUtilisateur().getId() : 0);
            ps.setInt(10, c.getPlat()       != null ? c.getPlat().getId()        : 0);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) c.setId(rs.getInt(1));
        }
        return c;
    }

    public void updateStatut(Commande c) throws SQLException {
        String sql = "UPDATE commandes SET statut = ? WHERE id = ?";
        try (PreparedStatement ps = DatabaseManager.getConnection().prepareStatement(sql)) {
            ps.setString(1, c.getStatut().name());
            ps.setInt(2, c.getId());
            ps.executeUpdate();
        }
    }

    public List<Commande> findAll() throws SQLException {
        List<Commande> list = new ArrayList<>();
        String sql = "SELECT c.*, " +
                "u.nom AS u_nom, u.email AS u_email, " +
                "p.nom AS p_nom, p.prix AS p_prix, " +
                "s.quantite AS s_qte, p.stock_id " +
                "FROM commandes c " +
                "LEFT JOIN utilisateurs u ON c.utilisateur_id = u.id " +
                "LEFT JOIN plats p        ON c.plat_id = p.id " +
                "LEFT JOIN stocks s        ON p.stock_id = s.id";
        try (Statement st = DatabaseManager.getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Utilisateur u = new Utilisateur(
                        rs.getInt("utilisateur_id"),
                        rs.getString("u_nom"),
                        rs.getString("u_email")
                );
                Stock stock = new Stock(rs.getInt("stock_id"), rs.getInt("s_qte"));
                Plat p = new Plat(
                        rs.getInt("plat_id"),
                        rs.getString("p_nom"),
                        rs.getDouble("p_prix"),
                        stock
                );
                Commande c = new Commande();
                c.setId(rs.getInt("id"));
                c.setReference(rs.getString("reference"));
                c.setNomRepas(rs.getString("nom_repas"));
                c.setNumeroClient(rs.getString("numero_client"));
                c.setQuantite(rs.getInt("quantite"));
                c.setDetails(rs.getString("details"));
                c.setStatut(StatutCommande.valueOf(rs.getString("statut")));
                c.setHeurePrete(rs.getTimestamp("heure_prete").toLocalDateTime());
                c.setUtilisateur(u);
                c.setPlat(p);
                list.add(c);
            }
        }
        return list;
    }
}

package com.elmamma.src.db;

import com.elmamma.src.model.Utilisateur;
import java.sql.*;

public class UtilisateurDAO {

    public Utilisateur insertOrUpdate(Utilisateur u) throws SQLException {
        String sql = "INSERT INTO utilisateurs (id, nom, email) VALUES (?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE nom = VALUES(nom), email = VALUES(email)";
        try (PreparedStatement ps = DatabaseManager.getConnection().prepareStatement(sql)) {
            ps.setInt(1, u.getId());
            ps.setString(2, u.getNom());
            ps.setString(3, u.getEmail());
            ps.executeUpdate();
        }
        return u;
    }

    public Utilisateur findById(int id) throws SQLException {
        String sql = "SELECT * FROM utilisateurs WHERE id = ?";
        try (PreparedStatement ps = DatabaseManager.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Utilisateur(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("email")
                );
            }
        }
        return null;
    }
}

package com.elmamma.src.db;

import com.elmamma.src.model.Plat;
import com.elmamma.src.model.Stock;
import java.sql.*;

public class PlatDAO {

    private final StockDAO stockDAO = new StockDAO();

    public Plat insert(Plat plat) throws SQLException {
        // persist stock first
        if (plat.getStock() != null && plat.getStock().getId() == 0) {
            stockDAO.insert(plat.getStock());
        }
        String sql = "INSERT INTO plats (nom, prix, stock_id) VALUES (?, ?, ?)";
        try (PreparedStatement ps = DatabaseManager.getConnection()
                .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, plat.getNom());
            ps.setDouble(2, plat.getPrix());
            ps.setInt(3, plat.getStock() != null ? plat.getStock().getId() : 0);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) plat.setId(rs.getInt(1));
        }
        return plat;
    }

    public Plat findById(int id) throws SQLException {
        String sql = "SELECT p.*, s.quantite FROM plats p " +
                "LEFT JOIN stocks s ON p.stock_id = s.id WHERE p.id = ?";
        try (PreparedStatement ps = DatabaseManager.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Stock stock = new Stock(rs.getInt("stock_id"), rs.getInt("quantite"));
                return new Plat(rs.getInt("id"), rs.getString("nom"),
                        rs.getDouble("prix"), stock);
            }
        }
        return null;
    }
}

package com.elmamma.src.db;

import com.elmamma.src.model.Stock;
import java.sql.*;

public class StockDAO {

    public Stock insert(Stock stock) throws SQLException {
        String sql = "INSERT INTO stocks (quantite) VALUES (?)";
        try (PreparedStatement ps = DatabaseManager.getConnection()
                .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, stock.getQuantite());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) stock.setId(rs.getInt(1));
        }
        return stock;
    }

    public void updateQuantite(Stock stock) throws SQLException {
        String sql = "UPDATE stocks SET quantite = ? WHERE id = ?";
        try (PreparedStatement ps = DatabaseManager.getConnection().prepareStatement(sql)) {
            ps.setInt(1, stock.getQuantite());
            ps.setInt(2, stock.getId());
            ps.executeUpdate();
        }
    }

    public Stock findById(int id) throws SQLException {
        String sql = "SELECT * FROM stocks WHERE id = ?";
        try (PreparedStatement ps = DatabaseManager.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Stock(rs.getInt("id"), rs.getInt("quantite"));
            }
        }
        return null;
    }
}

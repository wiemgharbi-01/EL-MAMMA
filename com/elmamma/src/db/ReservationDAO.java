package com.elmamma.src.db;

import com.elmamma.src.model.Reservation;
import java.sql.*;

public class ReservationDAO {

    public Reservation insert(Reservation r) throws SQLException {
        String sql = "INSERT INTO reservations (date_reservation, utilisateur_id, plat_id) VALUES (?, ?, ?)";
        try (PreparedStatement ps = DatabaseManager.getConnection()
                .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setTimestamp(1, Timestamp.valueOf(r.getDateReservation()));
            ps.setInt(2, r.getEtudiant() != null ? r.getEtudiant().getId() : 0);
            ps.setInt(3, r.getPlat()     != null ? r.getPlat().getId()     : 0);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) r.setId(rs.getInt(1));
        }
        return r;
    }
}

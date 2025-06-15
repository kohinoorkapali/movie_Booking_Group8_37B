/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import java.sql.*;

public class SeatDAO {
    private Connection conn;

    public SeatDAO() throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/seat_plan?user=root&password=N9neplusone");
        initializeDatabase();
    }

    private void initializeDatabase() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS vip_bookings (" +
                     "seat_number TEXT PRIMARY KEY, " +
                     "booked_by TEXT NOT NULL)";
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        }
    }

    public String getBookedBy(String seatNumber) throws SQLException {
        String sql = "SELECT booked_by FROM vip_bookings WHERE seat_number = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, seatNumber);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("booked_by");
            }
        }
        return null;
    }

    public boolean bookSeat(String seatNumber, String name) throws SQLException {
        String sql = "INSERT INTO vip_bookings (seat_number, booked_by) VALUES (?, ?) " +
                     "ON CONFLICT(seat_number) DO UPDATE SET booked_by = excluded.booked_by";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, seatNumber);
            stmt.setString(2, name);
            stmt.executeUpdate();
            return true;
        }
    }

    public boolean releaseSeat(String seatNumber) throws SQLException {
        String sql = "DELETE FROM vip_bookings WHERE seat_number = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, seatNumber);
            stmt.executeUpdate();
            return true;
        }
    }

    public void close() throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }
}

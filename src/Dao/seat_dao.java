/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import java.sql.*;
import java.util.*;
import Model.seat_model;

public class seat_dao {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/seat_plan";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "N9neplusone";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }

    public Map<String, seat_model> getAllBookings() {
        Map<String, seat_model> seatMap = new HashMap<>();
        String sql = "SELECT * FROM seat_details";

        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                String seatNumber = rs.getString("seat_number");
                String username = rs.getString("booked_by_username"); // only username

                seat_model seat = new seat_model(seatNumber, username);
                seatMap.put(seatNumber, seat);
            }

        } catch (SQLException e) {
            System.out.println("Error loading bookings: " + e.getMessage());
        }

        return seatMap;
    }

    public boolean bookSeat(String seatNumber, String username) {
        String sql = "UPDATE seat_details SET booked_by_username = ? WHERE seat_number = ?";

        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, username);
            pst.setString(2, seatNumber);

            int rows = pst.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Failed to book: " + e.getMessage());
            return false;
        }
    }

    public boolean cancelSeat(String seatNumber) {
        String sql = "UPDATE seat_details SET booked_by_username = NULL WHERE seat_number = ?";

        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, seatNumber);

            int rows = pst.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Failed to cancel: " + e.getMessage());
            return false;
        }
    }

    public seat_model getBookingBySeat(String seatNumber) {
        String sql = "SELECT * FROM seat_details WHERE seat_number = ?";

        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, seatNumber);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    String username = rs.getString("booked_by_username");
                    return new seat_model(seatNumber, username);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error getting seat: " + e.getMessage());
        }

        return null;
    }
}
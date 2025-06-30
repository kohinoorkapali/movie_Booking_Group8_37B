/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import Model.PaymentModel;
import Database.MySqlConnection;

/**
 *
 * @author Kohinoor
 */
public class PaymentDAO {
     private MySqlConnection mySqlConnection = new MySqlConnection();

    public boolean savePayment(PaymentModel payment) {
        String sql = "INSERT INTO payments (user_id, movie_title, seats, total_price, payment_method) VALUES (?, ?, ?, ?, ?)";

        Connection con = mySqlConnection.openConnection();
        if (con == null) return false;

        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, payment.getUserId());
            pst.setString(2, payment.getMovieTitle());
            pst.setString(3, payment.getSeats());
            pst.setDouble(4, payment.getTotalPrice());
            pst.setString(5, payment.getPaymentMethod());

            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Failed to save payment: " + e.getMessage());
            return false;
        } finally {
            mySqlConnection.closeConnection(con);
        }
    }

    // Check if user has confirmed booking for given movie
    public boolean hasBooking(int userId, String movieTitle) {
        String sql = "SELECT COUNT(*) FROM payments WHERE user_id = ? AND movie_title = ?";

        Connection con = mySqlConnection.openConnection();
        if (con == null) return false;

        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, userId);
            pst.setString(2, movieTitle);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println("Failed to check booking: " + e.getMessage());
        } finally {
            mySqlConnection.closeConnection(con);
        }
        return false;
    }

    // Get booked seats string for user and movie
   public String getBookedSeats(int userId, String movieTitle) {
    String sql = "SELECT seats FROM payments WHERE user_id = ? AND movie_title = ? AND status = 'confirmed'";
    Connection con = mySqlConnection.openConnection();
    if (con == null) return null;

    StringBuilder seatsBuilder = new StringBuilder();

    try (PreparedStatement pst = con.prepareStatement(sql)) {
        pst.setInt(1, userId);
        pst.setString(2, movieTitle);

        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            String seat = rs.getString("seats");
            if (seatsBuilder.length() > 0) {
                seatsBuilder.append(", ");
            }
            seatsBuilder.append(seat);
        }
        return seatsBuilder.toString();
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        mySqlConnection.closeConnection(con);
    }
    return null;
}


    // Get booking price for user and movie
    public double getBookingPrice(int userId, String movieTitle) {
        String sql = "SELECT total_price FROM payments WHERE user_id = ? AND movie_title = ?";

        Connection con = mySqlConnection.openConnection();
        if (con == null) return 0;

        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, userId);
            pst.setString(2, movieTitle);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getDouble("total_price");
            }
        } catch (SQLException e) {
            System.out.println("Failed to get booking price: " + e.getMessage());
        } finally {
            mySqlConnection.closeConnection(con);
        }
        return 0;
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Database.MySqlConnection;
import Model.PayConfirmModel;

/**
 *
 * @author Kohinoor
 */
public class PayConfirmDao {
    private MySqlConnection connection = new MySqlConnection();

    public PayConfirmModel fetchConfirmation(int paymentId) {
        String sql = "SELECT user_id, movie_title, seats, total FROM payments WHERE payment_id = ?";
        try (Connection conn = connection.openConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, paymentId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return new PayConfirmModel(
                    rs.getInt("user_id"),
                    rs.getString("movie_title"),
                    rs.getString("seats"),
                    rs.getInt("total")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // or throw exception
    }
}

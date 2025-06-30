/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Database.MySqlConnection;

/**
 *
 * @author Kohinoor
 */
public class CancelDao {
     private MySqlConnection mySqlConnection = new MySqlConnection();

    public boolean cancelBooking(String movieTitle, int userId) {
        Connection conn = mySqlConnection.openConnection();
    if (conn == null) return false;

    try {
        // Step 1: Free up seats
        String freeSeatsSql = "UPDATE seat_details " +
                      "SET booked_by_user_id = NULL, booked_for_name = NULL " +
                      "WHERE movie_id = (SELECT id FROM movies WHERE title = ?) " +
                      "AND booked_by_user_id = ?";

        PreparedStatement freeSeatsStmt = conn.prepareStatement(freeSeatsSql);
freeSeatsStmt.setString(1, movieTitle);
freeSeatsStmt.setInt(2, userId);
freeSeatsStmt.executeUpdate();

        // Step 2: Update payments table
        String cancelPaymentSql = "UPDATE payments " +
                                  "SET status = 'canceled' " +
                                  "WHERE movie_title = ? AND user_id = ? AND status = 'confirmed'";

        PreparedStatement cancelStmt = conn.prepareStatement(cancelPaymentSql);
        cancelStmt.setString(1, movieTitle);
        cancelStmt.setInt(2, userId);
        int affected = cancelStmt.executeUpdate();

        return affected > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    } finally {
        mySqlConnection.closeConnection(conn);
    }
}
}

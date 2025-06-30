/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Database.MySqlConnection;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Nishan
 */
public class ReviewDa {
    MySqlConnection mysql = new MySqlConnection();
    public boolean submitRating( int userId, int rating, String comment) {
        Connection conn = mysql.openConnection();
        String sql = "INSERT INTO reviews (user_id, rating,comment) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, userId);
            pstmt.setInt(2, rating);
            pstmt.setString(3, comment);
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            Logger.getLogger(ReviewDa.class.getName()).log(Level.SEVERE, null, e);
            return false;
        } finally {
            mysql.closeConnection(conn);
        }
    }
}

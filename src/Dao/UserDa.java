package Dao;

import Database.MySqlConnection;

import Model.SignUp1;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UserDa {
    MySqlConnection mysql = new MySqlConnection();
  
    // Sign-up
    public boolean signUp(SignUp1 user) {
        Connection conn = mysql.openConnection();
        String sql = "INSERT INTO user_DB (email, username, password) VALUES (?, ?, ?)";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUserEmail());
            pstmt.setString(2, user.getUserName());
            pstmt.setString(3, user.getUserPassword());

            int result = pstmt.executeUpdate();
            return result >0;
        } catch (SQLException e) {
            Logger.getLogger(UserDa.class.getName()).log(Level.SEVERE, null, e);
            return false;
        } finally {
            mysql.closeConnection(conn);
        }
    }
    
    public boolean emailExists(String email) {
        Connection conn = mysql.openConnection();
        String sql = "SELECT id FROM user_DB WHERE email = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            return rs.next(); // returns true if email exists
        } catch (SQLException e) {
            Logger.getLogger(UserDa.class.getName()).log(Level.SEVERE, null, e);
            return true;
        } finally {
            mysql.closeConnection(conn);
        }
    }
    
}
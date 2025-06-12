package Dao;

import Database.MySqlConnection;
import Model.SignUp1;
import Model.Security_SignUp;
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
        String sql = "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUserName());
            pstmt.setString(2, user.getUserEmail().trim().toLowerCase());
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
    System.out.println("Checking email in DB: [" + email + "]");
    Connection conn = mysql.openConnection();
    if (conn == null) return false;

    String query = "SELECT email FROM users WHERE LOWER(email) = ?";
    
    try (PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setString(1, email.trim().toLowerCase());
        ResultSet rs = stmt.executeQuery();
        boolean exists = rs.next();
        rs.close();
        return exists;
    } catch (Exception e) {
        System.out.println("Error checking email: " + e.getMessage());
        return false;
    } finally {
        mysql.closeConnection(conn);
    }
}
public boolean saveSecurityAnswersByEmail(String email, String answer1, String answer2) {
    Connection conn = mysql.openConnection();
    String sql = "INSERT INTO security_questions (email, answer1, answer2) " +
                 "VALUES (?, ?, ?) " +
                 "ON DUPLICATE KEY UPDATE answer1 = VALUES(answer1), answer2 = VALUES(answer2)";
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, email.trim().toLowerCase());
        pstmt.setString(2, answer1);
        pstmt.setString(3, answer2);

        int result = pstmt.executeUpdate();
        return result > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    } finally {
        mysql.closeConnection(conn);
    }
}


}
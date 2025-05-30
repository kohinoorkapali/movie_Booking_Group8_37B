/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;
import Database.Database;
import Database.MySqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 *
 * @author Kohinoor
 */
public class UserDao {

    private final Database db = new MySqlConnection();

    
    public boolean emailExists(String email) {
         System.out.println("Checking email in DB: [" + email + "]");
    Connection conn = db.openConnection();
    if (conn == null) return false;

    String query = "SELECT email FROM users WHERE LOWER(email) = ?";
    
    try (PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setString(1, email.trim().toLowerCase());  // Trim & lowercase
        ResultSet rs = stmt.executeQuery();
        boolean exists = rs.next();
        rs.close();
        return exists;
    } catch (Exception e) {
        System.out.println("Error checking email: " + e.getMessage());
        return false;
    } finally {
        db.closeConnection(conn);
    
}

    }

    public boolean validateSecurityAnswers(String email, String ans1, String ans2) {
        Connection conn = db.openConnection();
        if (conn == null) return false;

        String query = "SELECT * FROM security_questions WHERE LOWER(email) = ? AND LOWER(TRIM(answer1)) = ? AND LOWER(TRIM(answer2)) = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email.trim().toLowerCase());
            stmt.setString(2, ans1.trim().toLowerCase());
            stmt.setString(3, ans2.trim().toLowerCase());

            ResultSet rs = stmt.executeQuery();
            boolean valid = rs.next();
            rs.close();
            return valid;
        } catch (Exception e) {
            System.out.println("Error validating answers: " + e.getMessage());
            return false;
        } finally {
            db.closeConnection(conn);
        }
    }

    public boolean resetPassword(String email, String newPassword) {
        Connection conn = db.openConnection();
        if (conn == null) return false;

        String query = "UPDATE users SET password = ? WHERE email = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, newPassword);
            stmt.setString(2, email);
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            System.out.println("Error resetting password: " + e.getMessage());
            return false;
        } finally {
            db.closeConnection(conn);
        }
    }


}

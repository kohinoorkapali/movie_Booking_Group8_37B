/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;
import Database.MySqlConnection;
import Model.UserData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author Kohinoor
 */
public class UserDao {
    MySqlConnection mySql =new MySqlConnection();
    Connection conn = mySql.openConnection();

    public void signUp(UserData user){
        String sql ="INSERT into users(username,email,password) values(?,?,?)";
        try(PreparedStatement pstmt =conn.prepareStatement(sql)) {
            pstmt.setString(1,user.getUsername());
            pstmt.setString(2,user.getEmail());
            pstmt.setString(3,user.getPassword());
            pstmt.executeUpdate();
        }catch(SQLException ex){
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE,null,ex);
        }  finally{
            mySql.closeConnection(conn);
        }
    }
    
     public boolean verifySecurityQuestion(String username, String answer) {
//        Connection conn = MySql.openConnection();
        String sql = "SELECT * FROM users WHERE username = ? AND security_answer = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, answer);
            ResultSet result = pstmt.executeQuery();
            return result.next(); // Returns true if the username and answer match
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            mySql.closeConnection(conn);
        }
        return false;
    }
    public boolean updatePassword(String username, String newPassword) {
//        Connection conn = MySql.openConnection();
        String sql = "UPDATE users SET password = ? WHERE username = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newPassword);
            pstmt.setString(2, username);
            pstmt.executeUpdate();
            return true; // Return true if password update is successful
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            mySql.closeConnection(conn);
        }
        return false;
    }
}

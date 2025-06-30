package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Database.MySqlConnection;
import Model.loginpage;

public class loginpagedao {

    MySqlConnection connection = new MySqlConnection();

     // Helper class to hold login result
    public static class LoginResult {
        private Integer userId;
        private String role;

        public LoginResult(Integer userId, String role) {
            this.userId = userId;
            this.role = role;
        }
        public Integer getUserId() {
            return userId;
        }
        public String getRole() {
            return role;
        }
    }

    // Returns LoginResult if credentials match, else null
    public LoginResult loginAndGetUserRole(loginpage user) {
        LoginResult loginResult = null;

        String sql = "SELECT user_id, role FROM users WHERE username = ? AND password = ?";
        try (Connection conn = connection.openConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getUsername().trim());
            stmt.setString(2, user.getPassword().trim());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Integer userId = rs.getInt("user_id");
                    String role = rs.getString("role");
                    loginResult = new LoginResult(userId, role);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return loginResult;
    }
}
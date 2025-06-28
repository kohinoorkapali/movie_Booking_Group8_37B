/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Database.MySqlConnection; // your shared connection class
import Model.ProfileModel;
import java.sql.*;

public class profile_dao {

    private final MySqlConnection mySqlConnection = new MySqlConnection();

    public ProfileModel getProfile(int userId) {
        ProfileModel model = null;
        String sql = "SELECT username FROM users WHERE user_id = ?";
        try (Connection conn = mySqlConnection.openConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    model = new ProfileModel(userId, rs.getString("username"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return model;
    }

    public boolean updateProfile(ProfileModel model) {
        String sql = "UPDATE users SET username = ? WHERE user_id = ?";
        try (Connection conn = mySqlConnection.openConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, model.getUsername());
            stmt.setInt(2, model.getUserId());

            int updated = stmt.executeUpdate();
            return updated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isUsernameTaken(String username, int excludeUserId) {
        String sql = "SELECT COUNT(*) FROM users WHERE username = ? AND user_id <> ?";
        try (Connection conn = mySqlConnection.openConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setInt(2, excludeUserId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

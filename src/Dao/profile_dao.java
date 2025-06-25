/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import java.sql.*;
import Model.profile_model;

public class profile_dao {

    private final String URL = "jdbc:mysql://localhost:3306/edit_profile"; 
    private final String USER = "root";
    private final String PASS = "N9neplusone";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    public profile_model getProfile() {
        profile_model model = null;
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT username FROM userprofile LIMIT 1")) {

            if (rs.next()) {
                model = new profile_model(rs.getString("username"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return model;
    }

    public boolean updateProfile(profile_model model) {
        String sql = "UPDATE userprofile SET username = ? LIMIT 1";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, model.getUsername());
            int updated = stmt.executeUpdate();
            return updated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

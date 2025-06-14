/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.Profile_ishan;
import java.sql.*;

public class ProfileDAO {

    private Connection connection;

    // Constructor: Initialize DB connection
    public ProfileDAO() {
        try {
            // Load the MySQL JDBC driver (optional for newer versions)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to your DB: replace with your DB URL, username, password
            String url = "jdbc:mysql://localhost:3306/edit_profile?useSSL=false&serverTimezone=UTC";
            String user = "root";
            String password = "N9neplusone";  

            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to connect to DB");
        }
    }

    // Update username and email for a given profile by ID
    public boolean updateUsername(Profile_ishan profile) {
        String sql = "UPDATE userProfile SET username = ?, email = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, profile.getUsername());
            stmt.setString(2, profile.getEmail());
            stmt.setInt(3, profile.getId());

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Fetch profile by ID
    public Profile_ishan getProfileById(int id) {
        String sql = "SELECT id, username, email FROM userProfile WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Profile_ishan profile = new Profile_ishan();
                profile.setId(rs.getInt("id"));
                profile.setUsername(rs.getString("username"));
                profile.setEmail(rs.getString("email"));
                return profile;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.movie_app;
import view.RatingUI;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class RatingMain {

    // Change these constants to your DB credentials
    private static final String URL = "jdbc:mysql://localhost:3306/review_rating?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";       
    private static final String PASSWORD = "N9neplusone"; 

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // Load MySQL JDBC Driver (optional for modern drivers)
                Class.forName("com.mysql.cj.jdbc.Driver");

                // Establish connection
                Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

                // Sample movie and user IDs (replace with real values in your app)
                int movieId = 1;
                int userId = 1001;

                // Open the rating UI
                RatingUI ratingUI = new RatingUI(conn, movieId, userId);
                ratingUI.setVisible(true);

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "MySQL JDBC Driver not found: " + e.getMessage(),
                        "Driver Error", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Database connection failed: " + e.getMessage(),
                        "Connection Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}

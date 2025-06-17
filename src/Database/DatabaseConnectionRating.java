/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionRating {

    private static final String URL = "jdbc:mysql://localhost:3306/review_rating";
    private static final String USER = "root";  // your MySQL username
    private static final String PASSWORD = "N9neplusone";  

    public static Connection getConnection() {
        try {
            // Load the MySQL JDBC driver (optional for recent Java versions)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish and return the connection
            return DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Failed to connect to database.");
            e.printStackTrace();
        }
        return null; // Return null if connection failed
    }
}

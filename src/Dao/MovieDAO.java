/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;
import Database.MySqlConnection;
import Model.Movie_add;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Kohinoor
 */
public class MovieDAO {
     private MySqlConnection mySqlConnection;
    public MovieDAO() {
        mySqlConnection = new MySqlConnection();
    }
    public void addMovie(Movie_add movie) {
        String query = "INSERT INTO movies (id, title, genre, synopsis, duration, show_date, image_path) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        try {
            conn = mySqlConnection.openConnection(); // Use your existing connection method
            if (conn != null) {
                try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                    pstmt.setString(1, movie.getId());
                    pstmt.setString(2, movie.getTitle());
                    pstmt.setString(3, movie.getGenre());
                    pstmt.setString(4, movie.getSynopsis());
                    pstmt.setString(5, movie.getDuration());
                    // Convert showDate string to java.sql.Date
                    SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
                    java.util.Date utilDate = sdf.parse(movie.getShowDate());
                    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                    pstmt.setDate(6, sqlDate);
                    pstmt.setString(7, movie.getImagePath());
                    pstmt.executeUpdate();
                }
            }
        } catch (SQLException | java.text.ParseException e) {
            e.printStackTrace();
            throw new RuntimeException("Error saving movie: " + e.getMessage());
        } finally {
            mySqlConnection.closeConnection(conn);
        }
    }

public void deleteMovie(int id) {
    String query = "DELETE FROM movies WHERE id = ?";
    MySqlConnection mySqlConnection = new MySqlConnection(); // Create an instance of MySqlConnection
    try (Connection conn = mySqlConnection.openConnection(); // Use MySqlConnection to open the connection
         PreparedStatement stmt = conn.prepareStatement(query)) {
        
        stmt.setInt(1, id);
        int rowsAffected = stmt.executeUpdate();
        if (rowsAffected == 0) {
            throw new RuntimeException("No movie found with ID: " + id);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        throw new RuntimeException("Error deleting movie: " + e.getMessage());
    }
}
   public int getNextMovieId() {
        String query = "SELECT MAX(id) AS maxId FROM movies";
        int nextId = 1; // Default ID if no movies exist
        try (Connection conn = mySqlConnection.openConnection();
             ResultSet rs = mySqlConnection.runQuery(conn, query)) {
             
            if (rs != null && rs.next()) {
                nextId = rs.getInt("maxId") + 1; // Increment the max ID by 1
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Print the stack trace for debugging
            throw new RuntimeException("Error retrieving next movie ID: " + e.getMessage());
        }
        return nextId;
    }
   
   
   public List<Movie_add> getAllMovies() {
        List<Movie_add> movies = new ArrayList<>();
        String query = "SELECT id, title, genre, duration, show_date FROM movies"; // Adjust the query as per your database schema
        Connection conn = null;
        try {
            conn = mySqlConnection.openConnection(); // Use your existing connection method
            if (conn != null) {
                try (PreparedStatement pstmt = conn.prepareStatement(query);
                     ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        String id = rs.getString("id");
                        String title = rs.getString("title");
                        String genre = rs.getString("genre");
                        String duration = rs.getString("duration");
                        String showDate = rs.getString("show_date");
                        // Create a new Movie_add object and add it to the list
                        Movie_add movie = new Movie_add(id, title, genre, "", duration, showDate, "");
                        movies.add(movie);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mySqlConnection.closeConnection(conn); // Ensure the connection is closed
        }
        return movies; // Return the list of movies
    }
}
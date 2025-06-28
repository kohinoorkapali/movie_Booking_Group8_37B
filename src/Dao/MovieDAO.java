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
        String query = "INSERT INTO movies (id, title, genre, synopsis, duration, show_date, image_path, price) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";


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
                     pstmt.setDouble(8, movie.getPrice()); 
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
    String query = "SELECT id, title, genre, synopsis, duration, show_date, image_path, price FROM movies";

    Connection conn = null;
    try {
        conn = mySqlConnection.openConnection();
        if (conn != null) {
            try (PreparedStatement pstmt = conn.prepareStatement(query);
                 ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String id = rs.getString("id");
                    String title = rs.getString("title");
                    String genre = rs.getString("genre");
                    String duration = rs.getString("duration");
                    String showDate = rs.getString("show_date");
                    String imagePath = rs.getString("image_path");
                    String synopsis = rs.getString("synopsis");
                     double price = rs.getDouble("price");
                     Movie_add movie = new Movie_add(id, title, genre, synopsis, duration, showDate, imagePath, price);

                   
                    movies.add(movie);
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        mySqlConnection.closeConnection(conn);
    }
    return movies;
}

public void addToFavorites(int userId, String movieId) {
    String query = "INSERT INTO favorites (user_id, movie_id) VALUES (?, ?)";
    try (Connection conn = mySqlConnection.openConnection();
         PreparedStatement pstmt = conn.prepareStatement(query)) {
        pstmt.setInt(1, userId);
        pstmt.setString(2, movieId);
        pstmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public void removeFromFavorites(int userId, String movieId) {
    String query = "DELETE FROM favorites WHERE user_id = ? AND movie_id = ?";
    try (Connection conn = mySqlConnection.openConnection();
         PreparedStatement pstmt = conn.prepareStatement(query)) {
        pstmt.setInt(1, userId);
        pstmt.setString(2, movieId);
        pstmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public boolean isFavorite(int userId, String movieId) {
    boolean favorite = false;
    String query = "SELECT * FROM favorites WHERE user_id = ? AND movie_id = ?";
    try (Connection conn = mySqlConnection.openConnection();
         PreparedStatement pstmt = conn.prepareStatement(query)) {
        pstmt.setInt(1, userId);
        pstmt.setString(2, movieId);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                favorite = true;
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return favorite;
}

public List<Movie_add> getFavoriteMovies(int userId) {
    List<Movie_add> favoriteMovies = new ArrayList<>();
    String query = "SELECT m.* FROM movies m JOIN favorites f ON m.id = f.movie_id WHERE f.user_id = ?";
    try (Connection conn = mySqlConnection.openConnection();
         PreparedStatement pstmt = conn.prepareStatement(query)) {
        pstmt.setInt(1, userId);
        try (ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                // map rs to Movie_add and add to favoriteMovies
                favoriteMovies.add(new Movie_add(
                    rs.getString("id"),
                    rs.getString("title"),
                    rs.getString("genre"),
                    rs.getString("synopsis"),
                    rs.getString("duration"),
                    rs.getString("show_date"),
                    rs.getString("image_path"),
                    rs.getDouble("price")
                ));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return favoriteMovies;
}

public void addToWatchlist(int userId, String movieId) {
    String query = "INSERT INTO watchlist (user_id, movie_id) VALUES (?, ?)";
    try (Connection conn = mySqlConnection.openConnection();
         PreparedStatement pstmt = conn.prepareStatement(query)) {
        pstmt.setInt(1, userId);
        pstmt.setString(2, movieId);
        pstmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public void removeFromWatchlist(int userId, String movieId) {
    String query = "DELETE FROM watchlist WHERE user_id = ? AND movie_id = ?";
    try (Connection conn = mySqlConnection.openConnection();
         PreparedStatement pstmt = conn.prepareStatement(query)) {
        pstmt.setInt(1, userId);
        pstmt.setString(2, movieId);
        pstmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public boolean isInWatchlist(int userId, String movieId) {
    boolean inWatchlist = false;
    String query = "SELECT * FROM watchlist WHERE user_id = ? AND movie_id = ?";
    try (Connection conn = mySqlConnection.openConnection();
         PreparedStatement pstmt = conn.prepareStatement(query)) {
        pstmt.setInt(1, userId);
        pstmt.setString(2, movieId);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                inWatchlist = true;
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return inWatchlist;
}

public List<Movie_add> getWatchlistMovies(int userId) {
    List<Movie_add> watchlistMovies = new ArrayList<>();
    String query = "SELECT m.* FROM movies m JOIN watchlist w ON m.id = w.movie_id WHERE w.user_id = ?";
    try (Connection conn = mySqlConnection.openConnection();
         PreparedStatement pstmt = conn.prepareStatement(query)) {
        pstmt.setInt(1, userId);
        try (ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                watchlistMovies.add(new Movie_add(
                    rs.getString("id"),
                    rs.getString("title"),
                    rs.getString("genre"),
                    rs.getString("synopsis"),
                    rs.getString("duration"),
                    rs.getString("show_date"),
                    rs.getString("image_path"),
                    rs.getDouble("price")
                ));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return watchlistMovies;
}


public List<Movie_add> searchMoviesByTitle(String keyword) {
    List<Movie_add> matchedMovies = new ArrayList<>();
    String query = "SELECT id, title, genre, synopsis, duration, show_date, image_path, price FROM movies WHERE title LIKE ?";

    try (Connection conn = mySqlConnection.openConnection();
         PreparedStatement pstmt = conn.prepareStatement(query)) {
        
        pstmt.setString(1, "%" + keyword + "%");  // Case-insensitive partial match
        try (ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                String id = rs.getString("id");
                String title = rs.getString("title");
                String genre = rs.getString("genre");
                String duration = rs.getString("duration");
                String showDate = rs.getString("show_date");
                String imagePath = rs.getString("image_path");
                String synopsis = rs.getString("synopsis");
                double price = rs.getDouble("price");

                Movie_add movie = new Movie_add(id, title, genre, synopsis, duration, showDate, imagePath, price);
                matchedMovies.add(movie);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return matchedMovies;
}

public List<Movie_add> getMoviesByGenre(String genre) {
    List<Movie_add> movies = new ArrayList<>();
    String query = "SELECT id, title, genre, synopsis, duration, show_date, image_path, price FROM movies WHERE genre = ?";

    try (Connection conn = mySqlConnection.openConnection();
         PreparedStatement pstmt = conn.prepareStatement(query)) {
        
        pstmt.setString(1, genre);
        try (ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                String id = rs.getString("id");
                String title = rs.getString("title");
                String genreValue = rs.getString("genre");
                String synopsis = rs.getString("synopsis");
                String duration = rs.getString("duration");
                String showDate = rs.getString("show_date");
                String imagePath = rs.getString("image_path");
                double price = rs.getDouble("price");

                Movie_add movie = new Movie_add(id, title, genreValue, synopsis, duration, showDate, imagePath, price);
                movies.add(movie);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return movies;
}

    
}
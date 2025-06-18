/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Model.watchlist_model; // using your lowercase class name
import Database.DBConnection_Watchlist;

public class WatchlistDao {

    // Add a movie to the watchlist
    public static boolean addToWatchlist(int userId, int movieId) {
        String query = "INSERT INTO watchlist (user_id, movie_id) VALUES (?, ?)";
        try (Connection conn = DBConnection_Watchlist.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, movieId);
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            System.out.println("Error adding to watchlist: " + e.getMessage());
            return false;
        }
    }

    // Retrieve watchlist for a user
    public static List<watchlist_model> getWatchlistByUserId(int userId) {
        List<watchlist_model> watchlist = new ArrayList<>();
        String query = """
            SELECT w.id, w.user_id, w.movie_id, m.title
            FROM watchlist w
            JOIN movie m ON w.movie_id = m.movie_id
            WHERE w.user_id = ?
        """;

        try (Connection conn = DBConnection_Watchlist.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                watchlist_model w = new watchlist_model(
                    rs.getInt("id"),
                    rs.getInt("user_id"),
                    rs.getInt("movie_id"),
                    rs.getString("title")
                );
                watchlist.add(w);
            }

        } catch (SQLException e) {
            System.out.println("Error fetching watchlist: " + e.getMessage());
        }

        return watchlist;
    }

    // Remove a movie from watchlist
    public static boolean removeFromWatchlist(int userId, int movieId) {
        String query = "DELETE FROM watchlist WHERE user_id = ? AND movie_id = ?";
        try (Connection conn = DBConnection_Watchlist.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, movieId);
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            System.out.println("Error removing from watchlist: " + e.getMessage());
            return false;
        }
    }
}

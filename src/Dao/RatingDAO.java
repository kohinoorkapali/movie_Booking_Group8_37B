/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import model.RatingModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RatingDAO {

    private final Connection conn;

    public RatingDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * Saves a new rating or updates the existing one for a given user and movie.
     * @param rating The RatingModel object containing movieId, userId, and rating value.
     * @return true if the rating was saved or updated successfully; false otherwise.
     */
    public boolean saveOrUpdateRating(RatingModel rating) {
        String sql = "INSERT INTO ratings (movie_id, user_id, rating_value) " +
                     "VALUES (?, ?, ?) " +
                     "ON DUPLICATE KEY UPDATE rating_value = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, rating.getMovieId());
            stmt.setInt(2, rating.getUserId());
            stmt.setInt(3, rating.getRating());
            stmt.setInt(4, rating.getRating());
            int affected = stmt.executeUpdate();
            return affected > 0;
        } catch (SQLException e) {
            System.err.println("Error saving/updating rating: " + e.getMessage());
            return false;
        }
    }

    /**
     * Retrieves the rating given by a user for a specific movie.
     * @param movieId The movie ID.
     * @param userId The user ID.
     * @return Rating value if exists, -1 otherwise.
     */
    public int getRating(int movieId, int userId) {
        String sql = "SELECT rating_value FROM ratings WHERE movie_id = ? AND user_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, movieId);
            stmt.setInt(2, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("rating_value");
            }
        } catch (SQLException e) {
            System.err.println("Error fetching rating: " + e.getMessage());
        }
        return -1; // not rated yet
    }
}

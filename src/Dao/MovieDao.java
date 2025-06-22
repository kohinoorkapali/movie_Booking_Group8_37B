/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.Movie;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author nitan
 */
public class MovieDao {
     private Connection conn;

    public MovieDao(Connection conn) {
        this.conn = conn;
    }

    public List<Movie> searchMovies(String keyword) {
        List<Movie> movies = new ArrayList<>();
        String sql = "SELECT * FROM movies WHERE title LIKE ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                movies.add(new Movie(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("genre"),
                    rs.getInt("duration"),
                    rs.getString("poster_path")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return movies;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Database.MySqlConnection;
import Model.Movie_add;
import Model.Moviegenre;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author nitan
 */
public class MoviegenreDAO {
    private Connection con;
    
    public MoviegenreDAO(Connection con){
        this.con = con;
    }
   public List<Movie_add> getMoviesByGenre(String genre) {
        List<Movie_add> movies = new ArrayList<>();
        MySqlConnection connection = new MySqlConnection();

        String sql;
        boolean filterByGenre = genre != null && !genre.trim().isEmpty();

        try (Connection conn = connection.openConnection()) {
            if (filterByGenre) {
                sql = "SELECT * FROM movies WHERE genre = ?";
            } else {
                sql = "SELECT * FROM movies";  // Return all movies if genre empty
            }

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                if (filterByGenre) {
                    stmt.setString(1, genre);
                }
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        Movie_add movie = new Movie_add();
                        movie.setId(String.valueOf(rs.getString("id")));
                        movie.setTitle(rs.getString("title"));
                        movie.setGenre(rs.getString("genre"));
                        movie.setSynopsis(rs.getString("Synopsis"));
                        movie.setDuration(rs.getString("duration"));
                        movie.setShowDate(rs.getString("showdate"));
                        movie.setImagePath(rs.getString("imagepath"));
                        movie.setPrice(400.00);
                        movies.add(movie);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return movies;
    }
}

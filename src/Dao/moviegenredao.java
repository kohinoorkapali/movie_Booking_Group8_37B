/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.Movie;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import Database.Database;
import Database.MySqlConnection;


/**
 *
 * @author nitan
 */
public class moviegenredao {
  public List<Movie> getMoviesByGenre(String genre) {
      MySqlConnection connection = new MySqlConnection();
        List<Movie> movies = new ArrayList<>();
        try {
            Connection conn = connection.openConnection(); 
            String sql = "SELECT * FROM movies WHERE genre = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, genre);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Movie movie = new Movie();
                movie.setId(rs.getInt("id"));
                movie.setTitle(rs.getString("title"));
                movie.setGenre(rs.getString("genre"));
                movie.setPoster_Path(rs.getString("poster_path"));
                movies.add(movie);
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movies;
    }
}
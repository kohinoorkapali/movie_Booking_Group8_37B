/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Movie_data;

import Rough_model.model;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private static final String URL = "jdbc:mysql://localhost:3306/movie_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASSWORD = "N9neplusone";

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public List<model> fetchMovies() throws SQLException, ClassNotFoundException {
        List<model> movies = new ArrayList<>();
        String query = "SELECT movie_id, title, director, release_year, genre, duration_minutes FROM mppart_db";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                model movie = new model(
                    rs.getInt("movie_id"),
                    rs.getString("title"),
                    rs.getString("director"),
                    rs.getInt("release_year"),
                    rs.getString("genre"),
                    rs.getInt("duration_minutes")
                );
                movies.add(movie);
            }
        }
        return movies;
    }
}

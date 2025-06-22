/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import Dao.MovieDao;
import Model.Movie;
import java.sql.Connection;
import java.util.List;
/**
 *
 * @author nitan
 */
public class MovieController {
    private MovieDao movieDAO;

    public MovieController(Connection conn) {
        this.movieDAO = new MovieDao(conn);
    }

    public List<Movie> searchMovies(String keyword) {
        return movieDAO.searchMovies(keyword);
    }
    
}

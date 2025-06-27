/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Dao.MoviegenreDAO;
import Model.Movie_add;
import java.util.List;
import java.sql.Connection;

/**
 *
 * @author nitan
 */
public class MoviegenreController {
     private MoviegenreDAO moviegenreDAO;

    public MoviegenreController(Connection con) {
        this.moviegenreDAO = new MoviegenreDAO(con);
    }

    public List<Movie_add> getMoviesByGenre(String genre) {
        return moviegenreDAO.getMoviesByGenre(genre);
    }
}

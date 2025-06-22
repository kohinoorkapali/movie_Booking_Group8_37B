/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import Dao.moviegenredao;
import Model.Movie;
import java.util.List;
/**
 *
 * @author nitan
 */
public class moviegenrecontroller {
private moviegenredao movieDAO = new moviegenredao();
    public List<Movie> getMoviesByGenre(String genre) {
        return movieDAO.getMoviesByGenre(genre);
    }
}

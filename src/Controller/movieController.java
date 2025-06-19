/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.util.List;

import Dao.MovieDAO;
import Model.Movie_add;

/**
 *
 * @author Kohinoor
 */
public class movieController {
    private MovieDAO movieDAO;
    public movieController() {
        this.movieDAO = new MovieDAO();
    }
    public void addMovie(String title, String genre, String synopsis, String duration, String showDate, String imagePath) {
        // Generate a new ID automatically
        int id = movieDAO.getNextMovieId(); // Method to get the next available ID
        Movie_add movie = new Movie_add(String.valueOf(id), title, genre, synopsis, duration, showDate, imagePath);
        movieDAO.addMovie(movie);
    }
    
    public void deleteMovie(int id) {
        movieDAO.deleteMovie(id); // Call the DAO method to delete the movie
    }
    public List<Movie_add> getAllMovies() {
        return movieDAO.getAllMovies(); // Fetch all movies from the DAO
    }
}

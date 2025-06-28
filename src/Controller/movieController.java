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
    public void addMovie(String title, String genre, String synopsis, String duration, String showDate, String imagePath, double price) {
        // Generate a new ID automatically
        int id = movieDAO.getNextMovieId(); // Method to get the next available ID
        Movie_add movie = new Movie_add(String.valueOf(id), title, genre, synopsis, duration, showDate, imagePath, price);
movieDAO.addMovie(movie);

    }
    
    public void deleteMovie(int id) {
        movieDAO.deleteMovie(id); // Call the DAO method to delete the movie
    }
    public List<Movie_add> getAllMovies() {
        return movieDAO.getAllMovies(); // Fetch all movies from the DAO
    }
public List<Movie_add> getFavoriteMovies(int userId) {
    return movieDAO.getFavoriteMovies(userId);
}

public void addToWatchlist(int userId, Movie_add movie) {
    movieDAO.addToWatchlist(userId, movie.getId());
}

public List<Movie_add> getWatchlistMovies(int userId) {
    return movieDAO.getWatchlistMovies(userId);
}


public List<Movie_add> searchMovies(String keyword) {
    return new MovieDAO().searchMoviesByTitle(keyword);
}

public List<Movie_add> getMoviesByGenre(String genre) {
    return movieDAO.getMoviesByGenre(genre);
}

}

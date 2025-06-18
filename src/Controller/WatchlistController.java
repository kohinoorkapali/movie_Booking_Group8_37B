/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Dao.WatchlistDao;
import Model.watchlist_model;
import java.util.List;

public class WatchlistController {

    // Add a movie to a user's watchlist
    public boolean addMovieToWatchlist(int userId, int movieId) {
        return WatchlistDao.addToWatchlist(userId, movieId);
    }

    // Get all movies in a user's watchlist
    public List<watchlist_model> getUserWatchlist(int userId) {
        return WatchlistDao.getWatchlistByUserId(userId);
    }

    // Remove a movie from a user's watchlist
    public boolean removeMovieFromWatchlist(int userId, int movieId) {
        return WatchlistDao.removeFromWatchlist(userId, movieId);
    }
}

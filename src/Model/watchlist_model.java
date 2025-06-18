/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

public class watchlist_model {
    private int id;          // Watchlist entry ID (primary key)
    private int userId;      // User who added the movie
    private int movieId;     // Movie's unique ID
    private String title;    // Movie title (to display)

    // Constructor
    public watchlist_model(int id, int userId, int movieId, String title) {
        this.id = id;
        this.userId = userId;
        this.movieId = movieId;
        this.title = title;
    }

    // Getters
    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getMovieId() {
        return movieId;
    }

    public String getTitle() {
        return title;
    }

    // Setters if needed (optional)
    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

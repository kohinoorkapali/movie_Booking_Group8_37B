/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// Package: Rough_model (the model class)
package Rough_model;

public class model {
    private int movieId;
    private String title;
    private String director;
    private int releaseYear;
    private String genre;
    private int durationMinutes;

    public model(int movieId, String title, String director, int releaseYear, String genre, int durationMinutes) {
        this.movieId = movieId;
        this.title = title;
        this.director = director;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.durationMinutes = durationMinutes;
    }

    // Getters
    public int getMovieId() { return movieId; }
    public String getTitle() { return title; }
    public String getDirector() { return director; }
    public int getReleaseYear() { return releaseYear; }
    public String getGenre() { return genre; }
    public int getDurationMinutes() { return durationMinutes; }
}

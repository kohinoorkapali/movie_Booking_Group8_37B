/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Kohinoor
 */
public class CancelModel {
    private String movieTitle;  // String type for movie title
    private int userId;
    private String seats;
    private int price;

    // Constructor
    public CancelModel(String movieTitle, int userId, String seats, int price) {
        this.movieTitle = movieTitle;
        this.userId = userId;
        this.seats = seats;
        this.price = price;
    }

    // Getters
    public String getMovieTitle() {
        return movieTitle;
    }

    public int getUserId() {
        return userId;
    }

    public String getSeats() {
        return seats;
    }

    public int getPrice() {
        return price;
    }

}

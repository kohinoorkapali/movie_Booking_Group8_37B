/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Kohinoor
 */
public class PaymentModel {

    private int paymentId;
    private int userId;
    private String movieTitle;
    private String seats;          // comma-separated seat numbers
    private double totalPrice;
    private String paymentMethod;

    public PaymentModel(int userId, String movieTitle, String seats, double totalPrice, String paymentMethod) {
        this.userId = userId;
        this.movieTitle = movieTitle;
        this.seats = seats;
        this.totalPrice = totalPrice;
        this.paymentMethod = paymentMethod;
    }

    // Getters and setters
    public int getPaymentId() { return paymentId; }
    public void setPaymentId(int paymentId) { this.paymentId = paymentId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getMovieTitle() { return movieTitle; }
    public void setMovieTitle(String movieTitle) { this.movieTitle = movieTitle; }

    public String getSeats() { return seats; }
    public void setSeats(String seats) { this.seats = seats; }

    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
}



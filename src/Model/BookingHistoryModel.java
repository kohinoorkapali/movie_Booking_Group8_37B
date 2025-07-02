/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Kohinoor
 */
public class BookingHistoryModel {
 private int seatId;
    private String movieTitle;
    private String seatNumber;
    private String bookedFor;
    private double totalPrice;
    private String paymentMethod;
    private String status;
    private String bookingDate;
    private String bookingTime;

    public BookingHistoryModel(int seatId, String movieTitle, String seatNumber, String bookedFor,
                               double totalPrice, String paymentMethod, String status,
                               String bookingDate, String bookingTime) {
        this.seatId = seatId;
        this.movieTitle = movieTitle;
        this.seatNumber = seatNumber;
        this.bookedFor = bookedFor;
        this.totalPrice = totalPrice;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.bookingDate = bookingDate;
        this.bookingTime = bookingTime;
    }

    // Getters and setters
    public int getSeatId() { return seatId; }
    public void setSeatId(int seatId) { this.seatId = seatId; }

    public String getMovieTitle() { return movieTitle; }
    public void setMovieTitle(String movieTitle) { this.movieTitle = movieTitle; }

    public String getSeatNumber() { return seatNumber; }
    public void setSeatNumber(String seatNumber) { this.seatNumber = seatNumber; }

    public String getBookedFor() { return bookedFor; }
    public void setBookedFor(String bookedFor) { this.bookedFor = bookedFor; }

    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getBookingDate() { return bookingDate; }
    public void setBookingDate(String bookingDate) { this.bookingDate = bookingDate; }

    public String getBookingTime() { return bookingTime; }
    public void setBookingTime(String bookingTime) { this.bookingTime = bookingTime; }
}
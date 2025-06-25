/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

public class seat_model {
    private String seatNumber;
    private String bookedByUsername; // can be null if not booked

    public seat_model(String seatNumber, String bookedByUsername) {
        this.seatNumber = seatNumber;
        this.bookedByUsername = bookedByUsername;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public String getBookedByUsername() {
        return bookedByUsername;
    }

    public boolean isBooked() {
        return bookedByUsername != null && !bookedByUsername.isEmpty();
    }
}
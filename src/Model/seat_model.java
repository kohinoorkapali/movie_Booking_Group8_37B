/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

public class seat_model {
    private String seatNumber;
    private boolean isBooked;

    public seat_model(String seatNumber) {
        this.seatNumber = seatNumber;
        this.isBooked = false; // default is available
    }

    // Getter and Setter
    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void bookSeat() {
        this.isBooked = true;
    }

    public void releaseSeat() {
        this.isBooked = false;
    }

    @Override
    public String toString() {
        return seatNumber + " - " + (isBooked ? "Booked" : "Available");
    }
}

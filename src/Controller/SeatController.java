/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.seat_model;

public class SeatController {
    private seat_model model;

    public SeatController(seat_model model) {
        this.model = model;
    }

    public String getBookedBy(String seatNumber) {
        return model.getBookedBy(seatNumber);
    }

    public boolean bookSeat(String seatNumber, String name) {
        return model.bookSeat(seatNumber, name);
    }

    public boolean releaseSeat(String seatNumber) {
        return model.releaseSeat(seatNumber);
    }
}

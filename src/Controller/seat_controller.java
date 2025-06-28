/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Dao.seat_dao;
import Model.SeatModel;
import view.seat_planning_GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class seat_controller {
    private final seat_planning_GUI view;
    private final seat_dao dao;
    private final Map<JButton, String> seatButtonMap = new HashMap<>();
    private final int movieId;
    private final int currentUserId;

    public seat_controller(seat_planning_GUI view, seat_dao dao, int movieId, int currentUserId) {
        this.view = view;
        this.dao = dao;
        this.movieId = movieId;
        this.currentUserId = currentUserId;
        initController();
    }

    private void initController() {
    mapButtonsToSeats();
    addListeners();
    loadSeatsStatus();
    initializeSeatsInDatabase();
}

    private void mapButtonsToSeats() {
        seatButtonMap.put(view.getJButton1(), "VIP1");
        seatButtonMap.put(view.getJButton4(), "VIP2");

        seatButtonMap.put(view.getJButton7(), "SEAT3");
        seatButtonMap.put(view.getJButton2(), "SEAT4");
        seatButtonMap.put(view.getJButton5(), "SEAT5");
        seatButtonMap.put(view.getJButton8(), "SEAT6");
        seatButtonMap.put(view.getJButton3(), "SEAT7");
        seatButtonMap.put(view.getJButton6(), "SEAT8");
        seatButtonMap.put(view.getJButton9(), "SEAT9");
        seatButtonMap.put(view.getJButton10(), "SEAT10");
        seatButtonMap.put(view.getJButton11(), "SEAT11");
        seatButtonMap.put(view.getJButton12(), "SEAT12");
    }

    private void addListeners() {
        for (Map.Entry<JButton, String> entry : seatButtonMap.entrySet()) {
            JButton button = entry.getKey();
            String seatNumber = entry.getValue();

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    handleSeatClick(seatNumber, button);
                }
            });
        }
    }

    private void handleSeatClick(String seatNumber, JButton button) {
        SeatModel seat = dao.getBookingBySeat(movieId, seatNumber);

        if (seat == null) {
            JOptionPane.showMessageDialog(view, "Seat not found.");
            return;
        }

        // For VIP seats
        if (seatNumber.startsWith("VIP")) {
            if (seat.isBooked()) {
                JOptionPane.showMessageDialog(view, "This seat is booked by user ID: " + seat.getBookedByUserId() +
                        (seat.getBookedForName() != null ? " for " + seat.getBookedForName() : ""));
            } else {
                JOptionPane.showMessageDialog(view, "VIP seat is not booked.");
            }
            return;
        }
 // For regular seats
    if (seat.isBooked()) {
        // Just show booked message; no cancel or info dialog
        JOptionPane.showMessageDialog(view, "This seat is already booked.");
        return;
        } else {
            String name = JOptionPane.showInputDialog(view, "Enter name for whom you're booking this seat:");
            if (name != null && !name.trim().isEmpty()) {
                if (dao.bookSeat(movieId, seatNumber, currentUserId, name)) {
                    button.setBackground(java.awt.Color.BLUE);
                    JOptionPane.showMessageDialog(view, "Seat booked successfully for " + name);
                } else {
                    JOptionPane.showMessageDialog(view, "Failed to book seat.");
                }
            }
        }
    }
    
    private void loadSeatsStatus() {
        Map<String, SeatModel> seatMap = dao.getAllBookings(movieId);

        for (Map.Entry<JButton, String> entry : seatButtonMap.entrySet()) {
            JButton button = entry.getKey();
            String seatNumber = entry.getValue();

            SeatModel seat = seatMap.get(seatNumber);
            if (seat != null && seat.isBooked()) {
                if (seatNumber.startsWith("VIP")) {
                    button.setBackground(java.awt.Color.RED);
                } else {
                    button.setBackground(java.awt.Color.BLUE);
                }
            } else {
                if (!seatNumber.startsWith("VIP")) {
                    button.setBackground(java.awt.Color.YELLOW);
                }
            }
        }
    }

    private void initializeSeatsInDatabase() {
        List<String> allSeatNumbers = seatButtonMap.values().stream().collect(Collectors.toList());
        dao.initializeSeatsForMovie(movieId, allSeatNumbers);
        System.out.println("Initialized seats: " + allSeatNumbers);
    }
}

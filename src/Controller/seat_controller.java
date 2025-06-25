/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Dao.seat_dao;
import Model.seat_model;
import view.seat_planning_GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class seat_controller {

    private final seat_planning_GUI view;
    private final seat_dao dao;
    private final Map<JButton, String> seatButtonMap = new HashMap<>();

    public seat_controller(seat_planning_GUI view, seat_dao dao) {
        this.view = view;
        this.dao = dao;

        mapButtonsToSeats();
        initController();
    }

    private void mapButtonsToSeats() {
        // Map buttons to seat numbers exactly matching your DB
        seatButtonMap.put(view.getJButton1(), "VIP1");   // VIP fixed
        seatButtonMap.put(view.getJButton4(), "VIP2");   // VIP fixed

        seatButtonMap.put(view.getJButton7(), "SEAT3");
        seatButtonMap.put(view.getJButton2(), "SEAT4");
        seatButtonMap.put(view.getJButton5(), "SEAT5");
        seatButtonMap.put(view.getJButton8(), "SEAT6");
        seatButtonMap.put(view.getJButton3(), "SEAT7");
        seatButtonMap.put(view.getJButton9(), "SEAT8");
    }

    public void initController() {
        loadSeatsStatus();

        // Add action listeners for all buttons except VIP seats (which are fixed)
        for (JButton btn : seatButtonMap.keySet()) {
            String seatNumber = seatButtonMap.get(btn);
            if (!seatNumber.equals("VIP1") && !seatNumber.equals("VIP2")) {
                btn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        handleSeatClick(btn, seatNumber);
                    }
                });
            } else {
                // VIP seat - just show booked by message
                btn.addActionListener(e -> {
                    seat_model vipSeat = dao.getBookingBySeat(seatNumber);
                    if (vipSeat != null && vipSeat.getBookedByUsername() != null) {
                        JOptionPane.showMessageDialog(view,
                                "This seat is booked by " + vipSeat.getBookedByUsername(),
                                "VIP Seat Info",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                });
            }
        }
    }

    private void loadSeatsStatus() {
        Map<String, seat_model> bookings = dao.getAllBookings();

        for (Map.Entry<JButton, String> entry : seatButtonMap.entrySet()) {
            JButton btn = entry.getKey();
            String seatNumber = entry.getValue();
            seat_model seat = bookings.get(seatNumber);

            if (seat != null && seat.getBookedByUsername() != null && !seat.getBookedByUsername().isEmpty()) {
                btn.setBackground(seatNumber.startsWith("VIP") ? 
                    new java.awt.Color(255, 51, 51) : java.awt.Color.BLUE);
                btn.setToolTipText("Booked by: " + seat.getBookedByUsername());
            } else {
                // Not booked
                btn.setBackground(java.awt.Color.YELLOW);
                btn.setToolTipText("Available");
            }
        }
    }

    private void handleSeatClick(JButton btn, String seatNumber) {
        seat_model seat = dao.getBookingBySeat(seatNumber);

        if (seat != null && seat.getBookedByUsername() != null && !seat.getBookedByUsername().isEmpty()) {
            // Seat already booked - offer cancel
            int cancel = JOptionPane.showConfirmDialog(view,
                    "Seat already booked by " + seat.getBookedByUsername() + ".\nDo you want to cancel the booking?",
                    "Cancel Booking",
                    JOptionPane.YES_NO_OPTION);
            if (cancel == JOptionPane.YES_OPTION) {
                if (dao.cancelSeat(seatNumber)) {
                    btn.setBackground(java.awt.Color.YELLOW);
                    btn.setToolTipText("Available");
                    JOptionPane.showMessageDialog(view, "Booking cancelled.");
                } else {
                    JOptionPane.showMessageDialog(view, "Failed to cancel booking.");
                }
            }
        } else {
            // Seat is free - ask to book
            int book = JOptionPane.showConfirmDialog(view,
                    "Do you want to book " + seatNumber + "?",
                    "Book Seat",
                    JOptionPane.YES_NO_OPTION);
            if (book == JOptionPane.YES_OPTION) {
                String username = JOptionPane.showInputDialog(view, "Enter your username:");
                if (username != null && !username.trim().isEmpty()) {
                    boolean booked = dao.bookSeat(seatNumber, username.trim());
                    if (booked) {
                        btn.setBackground(java.awt.Color.BLUE);
                        btn.setToolTipText("Booked by: " + username.trim());
                        JOptionPane.showMessageDialog(view, "Seat booked successfully.");
                    } else {
                        JOptionPane.showMessageDialog(view, "Failed to book seat.");
                    }
                } else {
                    JOptionPane.showMessageDialog(view, "Username cannot be empty.");
                }
            }
        }
    }
}
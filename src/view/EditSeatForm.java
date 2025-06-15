/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import model.seat_model;
import javax.swing.*;
import java.awt.*;

public class EditSeatForm extends JFrame {
    private JLabel statusLabel;
    private JButton bookButton;
    private String seatNumber;
    private seat_model model;

    public EditSeatForm(String seatNumber, seat_model model) {
        this.seatNumber = seatNumber;
        this.model = model;

        setTitle("Seat Info: " + seatNumber);
        setSize(350, 150);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        statusLabel = new JLabel("", SwingConstants.CENTER);
        bookButton = new JButton("Book Seat");

        add(statusLabel, BorderLayout.CENTER);
        add(bookButton, BorderLayout.SOUTH);

        bookButton.addActionListener(e -> changeBooking());

        refreshStatus();
    }

    private void refreshStatus() {
        try {
            String name = model.getBookedBy(seatNumber);
            if (name != null) {
                statusLabel.setText("Booked by: " + name);
                bookButton.setText("Booked");
                bookButton.setEnabled(false);  // Disable booking edits if already booked
            } else {
                statusLabel.setText("Seat is available");
                bookButton.setText("Book Seat");
                bookButton.setEnabled(true);   // Enable booking for available seats
            }
        } catch (Exception e) {
            statusLabel.setText("Error loading booking");
            e.printStackTrace();
        }
    }

    private void changeBooking() {
        String input = JOptionPane.showInputDialog(
            this,
            "Enter name to book this seat:",
            "Booking - " + seatNumber,
            JOptionPane.PLAIN_MESSAGE
        );
        if (input != null && !input.trim().isEmpty()) {
            try {
                model.bookSeat(seatNumber, input.trim());
                refreshStatus();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error saving booking", "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Dao.seat_dao;
import Model.SeatModel;
import view.Payment;
import view.seat_planning_GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class seat_controller {
    private final Set<String> selectedSeats = new HashSet<>();
private final Map<String, String> bookingNames = new HashMap<>();  
    private final seat_planning_GUI view;
    private final seat_dao dao;
    private final Map<JButton, String> seatButtonMap = new HashMap<>();
    private final int movieId;
    private final int currentUserId;
    private final LocalDateTime showtime;

   public seat_controller(seat_planning_GUI view, seat_dao dao, int movieId, int currentUserId, LocalDateTime showtime) {
        this.view = view;
        this.dao = dao;
        this.movieId = movieId;
        this.currentUserId = currentUserId;
        this.showtime = showtime;  // new
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

    if (seat.isBooked()) {
        JOptionPane.showMessageDialog(view, "This seat is already booked.");
        return;
    }

    // Toggle selection in memory
    if (selectedSeats.contains(seatNumber)) {
        // Deselect
        selectedSeats.remove(seatNumber);
        bookingNames.remove(seatNumber);
        button.setBackground(java.awt.Color.YELLOW);
    } else {
        // Select
        String name = JOptionPane.showInputDialog(view, "Enter name for whom you're booking this seat:");
        if (name != null && !name.trim().isEmpty()) {
            selectedSeats.add(seatNumber);
            bookingNames.put(seatNumber, name.trim());
            button.setBackground(java.awt.Color.BLUE);
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

    public void onContinueClicked() {
     if (selectedSeats.isEmpty()) {
        JOptionPane.showMessageDialog(view, "Please select at least one seat.");
        return;
    }

    // Add this check here before proceeding:
    if (showtime.isBefore(LocalDateTime.now())) {
        JOptionPane.showMessageDialog(view, "Cannot book tickets. The movie showtime has already passed.", "Booking Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    double pricePerSeat = dao.getPricePerSeat(movieId);
    double totalPrice = pricePerSeat * selectedSeats.size();

    String seatsString = String.join(", ", selectedSeats);
    String movieTitle = dao.getMovieTitle(movieId);

    Payment paymentPage = new Payment(movieTitle, seatsString, (int) totalPrice, currentUserId, bookingNames);
    paymentPage.setBookingNames(bookingNames); // set booking map here
    PaymentController paymentController = new PaymentController(paymentPage, movieId, currentUserId);
    paymentPage.setController(paymentController);
    paymentPage.setVisible(true);
    view.dispose();
}

}

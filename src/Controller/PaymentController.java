/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import Dao.PaymentDAO;
import Dao.seat_dao;
import Model.PaymentModel;
import view.Payment;
import view.PaymentConfirmation;

import java.util.Map;

import javax.swing.*;

/**
 *
 * @author Kohinoor
 */
public class PaymentController {
     private Payment view;
    private int movieId;
    private int currentUserId;
    private PaymentDAO paymentDAO;
    private seat_dao seatDAO;
    
    
    public PaymentController(Payment view, int movieId, int currentUserId) {
    this.view = view;
    this.movieId = movieId;
    this.currentUserId = currentUserId;
    this.paymentDAO = new PaymentDAO();
    this.seatDAO = new seat_dao();
    initController();
}


    private void initController() {
        view.addProceedListener(e -> handleProceed());
    }

    public void handleProceed() {
     String paymentMethod = view.getSelectedPaymentMethod();
    if (paymentMethod.isEmpty()) {
        JOptionPane.showMessageDialog(view, "Please select a payment method.", "Payment Error", JOptionPane.WARNING_MESSAGE);
        return;
    }

    PaymentModel payment = new PaymentModel(
        currentUserId,
        view.getMovieTitle(),
        view.getSelectedSeats(),
        view.getTotalPrice(),
        paymentMethod
    );

    // 3. Save the payment info via DAO
    
    boolean paymentSuccess = paymentDAO.savePayment(payment);

    if (!paymentSuccess) {
        JOptionPane.showMessageDialog(view, "Payment failed. Please try again.", "Payment Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Book seats after payment
    Map<String, String> bookingNames = view.getBookingNames();
    boolean bookingSuccess = seatDAO.bookMultipleSeats(movieId, bookingNames, currentUserId);

    if (bookingSuccess) {
        JOptionPane.showMessageDialog(view, "Payment and seat booking successful! Thank you.");
 PaymentConfirmation confirmation = new PaymentConfirmation(
            view.getMovieTitle(),
            view.getSelectedSeats(),
            (int) view.getTotalPrice(),
            currentUserId
        );
        confirmation.setVisible(true);
        view.dispose();
    } else {
        JOptionPane.showMessageDialog(view, "Payment succeeded but failed to book seats. Contact support.", "Booking Error", JOptionPane.ERROR_MESSAGE);
    }
}
}


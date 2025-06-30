/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import javax.swing.JOptionPane;

import Dao.CancelDao;
import Model.CancelModel;
import view.Cancel_Booking;

/**
 *
 * @author Kohinoor
 */
public class CancelController {
      private Cancel_Booking view;
    private CancelModel model;
    private CancelDao dao;

    public CancelController(Cancel_Booking view, CancelModel model) {
        this.view = view;
        this.model = model;
        this.dao = new CancelDao();

        initView();
        initController();
    }

    private void initView() {
        // Use movieTitle instead of movieId (which does not exist)
        view.getLblMovie().setText(model.getMovieTitle());
        view.getLblSeats().setText(model.getSeats());
        view.getLblPrice().setText(String.valueOf(model.getPrice()));
    }

    private void initController() {
        view.getBtnConfirm().addActionListener(e -> cancelBooking());
    }

    private void cancelBooking() {
        int confirm = JOptionPane.showConfirmDialog(view, "Are you sure you want to cancel?", "Confirm Cancel", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            // Pass movieTitle (String) instead of movieId
            boolean result = dao.cancelBooking(model.getMovieTitle(), model.getUserId());
            if (result) {
                JOptionPane.showMessageDialog(view, "✅ Booking canceled. Refund of Rs. " + model.getPrice() + " issued.");
                view.getBtnConfirm().setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(view, "❌ Cancel failed. Try again.");
            }
        }
    }


}

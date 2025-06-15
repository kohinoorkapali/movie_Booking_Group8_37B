/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.movie_app;

import model.seat_model;
import view.EditSeatForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SeatMain {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            seat_model model = new seat_model();

            JFrame mainFrame = new JFrame("Seat Planner");
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setSize(800, 400);
            mainFrame.setLayout(new GridLayout(4, 5, 10, 10));  // 20 seats in 4 rows and 5 columns
            mainFrame.setLocationRelativeTo(null);

            String[] vipSeats = {"VIP 1", "VIP 2"};
            String[] allSeats = new String[20];
            allSeats[0] = "VIP 1";
            allSeats[1] = "VIP 2";
            for (int i = 2; i < 20; i++) {
                allSeats[i] = "Seat " + (i - 1);
            }

            for (String seat : allSeats) {
                JButton btn = new JButton(seat);

                // Color VIP seats RED always
                if (seat.equals("VIP 1") || seat.equals("VIP 2")) {
                    btn.setBackground(Color.RED);
                    btn.setOpaque(true);
                    btn.setBorderPainted(false);
                } else {
                    // For non-VIP seats, green if booked, else default
                    String bookedBy = model.getBookedBy(seat);
                    if (bookedBy != null) {
                        btn.setBackground(Color.GREEN);
                        btn.setOpaque(true);
                        btn.setBorderPainted(false);
                    }
                }

                btn.addActionListener(e -> {
                    EditSeatForm form = new EditSeatForm(seat, model);
                    form.setVisible(true);

                    // When the EditSeatForm closes, update button color accordingly
                    form.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                            // VIP seats remain red no matter what
                            if (seat.equals("VIP 1") || seat.equals("VIP 2")) {
                                btn.setBackground(Color.RED);
                            } else {
                                String bookedBy = model.getBookedBy(seat);
                                if (bookedBy != null) {
                                    btn.setBackground(Color.GREEN);
                                } else {
                                    btn.setBackground(null);  // reset to default color
                                }
                                btn.setOpaque(bookedBy != null);
                                btn.setBorderPainted(true);
                            }
                        }
                    });
                });

                mainFrame.add(btn);
            }

            mainFrame.setVisible(true);
        });
    }
}

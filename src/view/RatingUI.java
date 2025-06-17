/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.RatingController;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

/**
 * Simple UI for submitting a movie rating.
 */
public class RatingUI extends JFrame {

    private final int movieId;
    private final int userId;
    private final RatingController ratingController;

    public RatingUI(Connection connection, int movieId, int userId) {
        super("Rate Movie");
        this.movieId = movieId;
        this.userId = userId;
        this.ratingController = new RatingController(connection);

        initUI();
    }

    private void initUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);

        JButton rateButton = new JButton("Rate This Movie");
        rateButton.addActionListener(e -> openRatingDialog());

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(rateButton, BorderLayout.CENTER);
    }

    private void openRatingDialog() {
        JDialog dialog = new JDialog(this, "Rate Movie", true);
        dialog.setSize(350, 150);
        dialog.setLayout(new BorderLayout());
        dialog.setLocationRelativeTo(this);

        JLabel infoLabel = new JLabel("Click a star to rate:", SwingConstants.CENTER);
        dialog.add(infoLabel, BorderLayout.NORTH);

        JPanel starsPanel = new JPanel();
        JLabel[] stars = new JLabel[5];

        for (int i = 0; i < 5; i++) {
            final int starValue = i + 1;
            stars[i] = new JLabel("☆");
            stars[i].setFont(new Font("Serif", Font.PLAIN, 40));
            stars[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            stars[i].addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    // Update stars display
                    for (int j = 0; j < 5; j++) {
                        stars[j].setText(j < starValue ? "★" : "☆");
                    }

                    // Submit rating through controller
                    boolean success = ratingController.submitRating(movieId, userId, starValue);
                    if (success) {
                        JOptionPane.showMessageDialog(dialog,
                            "Thank you! You rated this movie " + starValue + " star(s).",
                            "Rating Saved",
                            JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(dialog,
                            "Failed to save rating. Please try again.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    }
                    dialog.dispose();
                }
            });

            starsPanel.add(stars[i]);
        }

        dialog.add(starsPanel, BorderLayout.CENTER);

        dialog.setVisible(true);
    }
}

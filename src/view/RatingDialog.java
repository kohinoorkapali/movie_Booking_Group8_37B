/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.RatingController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;

public class RatingDialog {

    public static void show(Window parent, Connection conn, int movieId, int userId) {
        JDialog dialog = new JDialog(parent, "Rate This Movie", Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setSize(350, 150);
        dialog.setLayout(new BorderLayout());
        dialog.setLocationRelativeTo(parent);

        JLabel[] stars = new JLabel[5];
        JPanel starPanel = new JPanel();
        starPanel.setLayout(new FlowLayout());

        JLabel infoLabel = new JLabel("Click a star to rate (1 = Poor, 5 = Extraordinary)", SwingConstants.CENTER);
        dialog.add(infoLabel, BorderLayout.NORTH);

        RatingController controller = new RatingController(conn);

        // To visually track current selected rating
        final int[] selectedRating = {0};

        for (int i = 0; i < 5; i++) {
            final int starValue = i + 1;
            stars[i] = new JLabel("☆");
            stars[i].setFont(new Font("Serif", Font.PLAIN, 36));
            stars[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            stars[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    selectedRating[0] = starValue;
                    // Update stars display
                    for (int j = 0; j < 5; j++) {
                        stars[j].setText(j < starValue ? "★" : "☆");
                    }
                    // Submit the rating
                    boolean success = controller.submitRating(movieId, userId, starValue);
                    if (success) {
                        JOptionPane.showMessageDialog(dialog, "Rating saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(dialog, "Failed to save rating.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    dialog.dispose();
                }
            });

            starPanel.add(stars[i]);
        }

        dialog.add(starPanel, BorderLayout.CENTER);
        dialog.setResizable(false);
        dialog.setVisible(true);
    }
}

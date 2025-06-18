/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.movie_app;

import view.WatchlistPanel;

import javax.swing.*;

public class Watchlist_main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Movie Booking App - Watchlist");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            int sampleUserId = 1;  // Replace with actual logged-in user ID
            WatchlistPanel watchlistPanel = new WatchlistPanel(sampleUserId);

            frame.add(watchlistPanel);
            frame.setSize(500, 600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}

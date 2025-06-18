/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import Controller.WatchlistController;
import Model.watchlist_model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class WatchlistPanel extends JPanel {
    private int userId;
    private WatchlistController controller;
    private JPanel movieListPanel;

    public WatchlistPanel(int userId) {
        this.userId = userId;
        this.controller = new WatchlistController();

        setLayout(new BorderLayout());
        JLabel heading = new JLabel("Your Watchlist", JLabel.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 20));
        add(heading, BorderLayout.NORTH);

        movieListPanel = new JPanel();
        movieListPanel.setLayout(new BoxLayout(movieListPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(movieListPanel);
        add(scrollPane, BorderLayout.CENTER);

        loadWatchlist();
    }

    private void loadWatchlist() {
        movieListPanel.removeAll();
        List<watchlist_model> watchlist = controller.getUserWatchlist(userId);

        if (watchlist.isEmpty()) {
            JLabel emptyLabel = new JLabel("No movies in your watchlist.");
            emptyLabel.setFont(new Font("Arial", Font.ITALIC, 14));
            movieListPanel.add(emptyLabel);
        } else {
            for (watchlist_model movie : watchlist) {
                JPanel row = new JPanel(new BorderLayout());
                row.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

                JLabel titleLabel = new JLabel(movie.getTitle());
                row.add(titleLabel, BorderLayout.WEST);

                JButton removeBtn = new JButton("Remove");
                removeBtn.addActionListener((ActionEvent e) -> {
                    boolean removed = controller.removeMovieFromWatchlist(userId, movie.getMovieId());
                    if (removed) {
                        JOptionPane.showMessageDialog(this, "Removed from watchlist.");
                        loadWatchlist(); // refresh list
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to remove.");
                    }
                });

                row.add(removeBtn, BorderLayout.EAST);
                movieListPanel.add(row);
            }
        }

        revalidate();
        repaint();
    }
}

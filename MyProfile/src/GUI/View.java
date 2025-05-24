/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

import Rough_model.model;
import controller.MovieController;

public class View extends JFrame {
    private final MovieController controller;
    private final JTable movieTable;

    public View(String username) {
        super("User Profile");

        controller = new MovieController();

        // Username label at the top
        JLabel usernameLabel = new JLabel("Welcome, " + username);
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        usernameLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        // Table to display movies
        movieTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(movieTable);

        // Buttons panel at the bottom
        JButton editProfileBtn = new JButton("Edit Profile");
        JButton viewMoviesBtn = new JButton("View Movies");
        JButton logoutBtn = new JButton("Logout");

        // Action only on View Movies for now
        viewMoviesBtn.addActionListener(e -> loadMovies());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.add(editProfileBtn);
        buttonPanel.add(viewMoviesBtn);
        buttonPanel.add(logoutBtn);

        // Layout setup
        setLayout(new BorderLayout());
        add(usernameLabel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setSize(700, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void loadMovies() {
        try {
            List<model> movies = controller.getMovies();

            String[] columnNames = {"ID", "Title", "Director", "Year", "Genre", "Duration (min)"};
            DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

            for (model m : movies) {
                Object[] row = {
                    m.getMovieId(),
                    m.getTitle(),
                    m.getDirector(),
                    m.getReleaseYear(),
                    m.getGenre(),
                    m.getDurationMinutes()
                };
                tableModel.addRow(row);
            }

            movieTable.setModel(tableModel);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error loading movies: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // Main method to launch GUI for testing
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new View("TestUser"));
    }
}

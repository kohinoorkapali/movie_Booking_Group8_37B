/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import Controller.MovieController;
import Model.Movie;
import Database.MySqlConnection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import javax.swing.JOptionPane;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import java.sql.Connection;
import javax.swing.Box;
import javax.swing.BoxLayout;
import java.awt.Image;

/**
 *
 * @author nitan
 */
public class Dashboard2 extends javax.swing.JFrame {

      // Add these instance variables
    private MovieController movieController;
    private List<Movie> allMovies;
    private List<Movie> filteredMovies;
    private JPanel moviesDisplayPanel;

    /**
     * Creates new form Dashboard
     */
    public Dashboard2() {
        initComponents();
        initializeMovieSystem();
        setupSearchFunctionality();
        setupMoviesDisplayArea();
        loadAllMovies();
    }
       private void initializeMovieSystem() {
    try {
        MySqlConnection dbConnection = new MySqlConnection();
        Connection conn = dbConnection.openConnection();

        if (conn != null) {
            movieController = new MovieController(conn);
        } else {
            JOptionPane.showMessageDialog(this, "Failed to connect to the database.");
            System.err.println("openConnection() returned null.");
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error connecting to database: " + e.getMessage());
        e.printStackTrace();
    }
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        historybutton = new javax.swing.JButton();
        homebutton = new javax.swing.JButton();
        moviebutton = new javax.swing.JButton();
        watchlistbutton = new javax.swing.JButton();
        logoutbuttton = new javax.swing.JButton();
        favouritebutton = new javax.swing.JButton();
        genres = new javax.swing.JComboBox<>();
        searchtextfield = new javax.swing.JTextField();
        logo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        searchbutton = new javax.swing.JButton();
        userbutton = new javax.swing.JButton();
        posterpanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(122, 106, 106));

        historybutton.setBackground(new java.awt.Color(122, 118, 132));
        historybutton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        historybutton.setText("HISTORY");

        homebutton.setBackground(new java.awt.Color(122, 118, 132));
        homebutton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        homebutton.setText("HOME");

        moviebutton.setBackground(new java.awt.Color(122, 118, 132));
        moviebutton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        moviebutton.setText("MOVIES");

        watchlistbutton.setBackground(new java.awt.Color(122, 118, 132));
        watchlistbutton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        watchlistbutton.setText("WATCHLIST");

        logoutbuttton.setBackground(new java.awt.Color(122, 118, 132));
        logoutbuttton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        logoutbuttton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/1828479.png"))); // NOI18N
        logoutbuttton.setText("LOG OUT");

        favouritebutton.setBackground(new java.awt.Color(122, 118, 132));
        favouritebutton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        favouritebutton.setText("FAVOURITE");

        genres.setBackground(new java.awt.Color(122, 118, 132));
        genres.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Categories", "All", "Action", "Comedy", "Drama", "Horror", "Fantasy", "Romance", "Thriller", "Sci-Fi", "Animation" }));
        genres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genresActionPerformed(evt);
            }
        });

        searchtextfield.setText("search anything");

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/hamro chalchitra logo.jpg"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Javanese Text", 1, 24)); // NOI18N
        jLabel1.setText("DASHBOARD");

        searchbutton.setBackground(new java.awt.Color(122, 106, 106));
        searchbutton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/loupe1.png"))); // NOI18N
        searchbutton.setBorderPainted(false);

        userbutton.setBackground(new java.awt.Color(122, 106, 106));
        userbutton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/user1.png"))); // NOI18N
        userbutton.setBorderPainted(false);

        javax.swing.GroupLayout posterpanelLayout = new javax.swing.GroupLayout(posterpanel);
        posterpanel.setLayout(posterpanelLayout);
        posterpanelLayout.setHorizontalGroup(
            posterpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 669, Short.MAX_VALUE)
        );
        posterpanelLayout.setVerticalGroup(
            posterpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 417, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(logo)
                .addGap(233, 233, 233)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(userbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(favouritebutton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(watchlistbutton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(historybutton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(logoutbuttton, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                    .addComponent(genres, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(51, 51, 51)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(homebutton, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(moviebutton, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(128, 128, 128)
                        .addComponent(searchtextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(posterpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(logo)
                        .addGap(3, 3, 3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(searchtextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(homebutton)
                                .addComponent(moviebutton)))
                        .addGap(62, 62, 62)
                        .addComponent(genres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(favouritebutton)
                        .addGap(41, 41, 41)
                        .addComponent(watchlistbutton)
                        .addGap(47, 47, 47)
                        .addComponent(historybutton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 120, Short.MAX_VALUE)
                        .addComponent(logoutbuttton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(userbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addComponent(searchbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(posterpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void genresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genresActionPerformed
        // TODO add your handling code here:
        String selectedCategory = (String) genres.getSelectedItem();
        if (selectedCategory != null && !selectedCategory.equals("Categories")) {
            filterMoviesByCategory(selectedCategory);
        }
    }//GEN-LAST:event_genresActionPerformed
   private void setupMoviesDisplayArea() {
        // Create a panel to display movies - you might need to add this panel to your existing layout
        moviesDisplayPanel = new JPanel();
        moviesDisplayPanel.setLayout(new GridLayout(0, 3, 15, 15)); // 3 columns with spacing
        moviesDisplayPanel.setBackground(new Color(240, 240, 240));
        
        // If you have a specific panel in your GUI to display movies, replace this with that panel
        // For now, I'll assume you want to add it to jPanel1 (adjust as needed)
        if (posterpanel != null) {
            posterpanel.setLayout(new BorderLayout());
            posterpanel.add(moviesDisplayPanel, BorderLayout.CENTER);
        }
    }
       
         private void loadAllMovies() {
        try {
            if (movieController != null) {
                allMovies = movieController.searchMovies(""); // Get all movies
                filteredMovies = allMovies;
                displayMovies(allMovies);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading movies: " + e.getMessage());
            e.printStackTrace();
        }
    }
         
          private void setupSearchFunctionality() {
        // Add KeyListener to search text field for real-time search
        if (searchtextfield != null) {
            searchtextfield.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    // Real-time search as user types (with slight delay)
                    javax.swing.SwingUtilities.invokeLater(() -> {
                        try {
                            Thread.sleep(300); // Small delay to avoid too many database calls
                        } catch (InterruptedException ex) {
                            Thread.currentThread().interrupt();
                        }
                        performSearch();
                    });
                }
                
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        performSearch();
                    }
                }
                
                @Override
                public void keyReleased(KeyEvent e) {
                    // Optional: implement real-time search here too
                    
                }
            });
            
            // Set placeholder text
            searchtextfield.setToolTipText("Search movies by title...");
            
        }
        
        // If you have a search button, add this code (replace jButton with your actual search button name)
        /*
        if (searchButton != null) {
            searchButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    performSearch();
                }
            });
        }
        */
    }
          private void performSearch() {
        try {
            if (movieController == null) {
                JOptionPane.showMessageDialog(this, "Movie system not initialized properly");
                return;
            }
            
            String searchKeyword = searchtextfield.getText().trim();
            
            if (searchKeyword.isEmpty()) {
                // If search is empty, show all movies
                filteredMovies = allMovies;
            } else {
                // Perform search using the controller
                filteredMovies = movieController.searchMovies(searchKeyword);
            }
            
            // Display the filtered results
            displayMovies(filteredMovies);
            
            // Update status label if you have one
            updateSearchStatus(searchKeyword, filteredMovies.size());
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error performing search: " + e.getMessage());
            e.printStackTrace();
        }
    }
  private void updateSearchStatus(String keyword, int resultCount) {
        String statusMessage;
        if (keyword.isEmpty()) {
            statusMessage = "Showing all movies (" + resultCount + ")";
        } else {
            statusMessage = "Found " + resultCount + " movie(s) for '" + keyword + "'";
        }
        
        // If you have a status label, update it here
        // Example: statusLabel.setText(statusMessage);
        
        // Show message if no results found
        if (resultCount == 0 && !keyword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No movies found matching '" + keyword + "'");
        }
    }
  
  private void displayMovies(List<Movie> movies) {
        if (moviesDisplayPanel == null) return;
        
        // Clear existing movies
        moviesDisplayPanel.removeAll();
        
        for(Movie movie : movies){
            JPanel movieCard = new JPanel(new BorderLayout());
            movieCard.setPreferredSize(new Dimension(180,300));
            movieCard.setBackground(Color.GRAY);
            
            ImageIcon icon = new ImageIcon("images/" + movie.getPoster_Path());
            Image img = icon.getImage().getScaledInstance(160, 200, Image.SCALE_SMOOTH);
            JLabel posterLabel = new JLabel(new ImageIcon(img));
            posterLabel.setHorizontalAlignment(SwingConstants.CENTER);
            
            JLabel title = new JLabel(movie.getTitle(), SwingConstants.CENTER);
            JLabel genre = new JLabel("Genre:" + movie.getGenre());
            JLabel duration = new JLabel("Duration:" + movie.getDuration()+ "minutes");
            
            
            JPanel infoPanel = new JPanel(new GridLayout(0,1));
            infoPanel.setBackground(Color.GRAY);
            infoPanel.add(title);
            infoPanel.add(genre);
            infoPanel.add(duration);
            
            movieCard.add(posterLabel, BorderLayout.CENTER);
            movieCard.add(infoPanel, BorderLayout.SOUTH);
            
            moviesDisplayPanel.add(movieCard); 
        }
        
        // Refresh the panel
        moviesDisplayPanel.revalidate();
        moviesDisplayPanel.repaint();
    }
   private JPanel createMovieCard(Movie movie) {
    JPanel card = new JPanel(new BorderLayout());
    card.setPreferredSize(new Dimension(220, 320));
    card.setBackground(Color.WHITE);
    card.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
        BorderFactory.createEmptyBorder(10, 10, 10, 10)
    ));

    //  Load Poster Image
    try {
        String posterPath = movie.getPoster_Path(); // e.g., "gladiator.jpg"
        ImageIcon posterIcon = new ImageIcon("images/" + posterPath);
        Image img = posterIcon.getImage().getScaledInstance(160, 200, Image.SCALE_SMOOTH);
        JLabel posterLabel = new JLabel(new ImageIcon(img));
        posterLabel.setHorizontalAlignment(SwingConstants.CENTER);
        card.add(posterLabel, BorderLayout.NORTH);
    } catch (Exception e) {
        JLabel errorLabel = new JLabel("Image not found");
        errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        card.add(errorLabel, BorderLayout.NORTH);
    }

    //  Movie title
    JLabel titleLabel = new JLabel("<html><center>" + movie.getTitle() + "</center></html>");
    titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
    titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
    titleLabel.setForeground(new Color(51, 51, 51));

    //  Movie details panel
    JPanel detailsPanel = new JPanel(new GridLayout(3, 1, 0, 5));
    detailsPanel.setBackground(Color.WHITE);

    JLabel genreLabel = new JLabel("Genre: " + movie.getGenre());
    genreLabel.setFont(new Font("Arial", Font.PLAIN, 12));

    JLabel durationLabel = new JLabel("Duration: " + movie.getDuration() + " minutes");
    durationLabel.setFont(new Font("Arial", Font.PLAIN, 12));

    JLabel idLabel = new JLabel("Movie ID: " + movie.getId());
    idLabel.setFont(new Font("Arial", Font.PLAIN, 11));
    idLabel.setForeground(Color.GRAY);

    detailsPanel.add(genreLabel);
    detailsPanel.add(durationLabel);
    detailsPanel.add(idLabel);

    //  Add title and details to center area (under poster)
    JPanel centerPanel = new JPanel();
    centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
    centerPanel.setBackground(Color.WHITE);
    centerPanel.add(Box.createVerticalStrut(5));
    centerPanel.add(titleLabel);
    centerPanel.add(detailsPanel);

    card.add(centerPanel, BorderLayout.CENTER);

    //  Hover and click effects
    card.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            onMovieSelected(movie);
        }

        @Override
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            card.setBackground(new Color(245, 245, 245));
            card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 149, 237), 2),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
            ));
        }

        @Override
        public void mouseExited(java.awt.event.MouseEvent evt) {
            card.setBackground(Color.WHITE);
            card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
            ));
        }
    });

    return card;
}
     private void onMovieSelected(Movie movie) {
        // Handle movie selection - you can open booking page, show details, etc.
        int choice = JOptionPane.showConfirmDialog(this, 
            "Movie: " + movie.getTitle() + "\n" +
            "Genre: " + movie.getGenre() + "\n" +
            "Duration: " + movie.getDuration() + " minutes\n\n" +
            "Would you like to book this movie?",
            "Movie Details",
            JOptionPane.YES_NO_OPTION);
            
        if (choice == JOptionPane.YES_OPTION) {
            // Navigate to booking page or open booking dialog
            openBookingPage(movie);
        }
    }
      private void openBookingPage(Movie movie) {
        // You can implement this based on your booking system
        // For example:
        // new BookingPage(movie).setVisible(true);
        // this.setVisible(false);
        
        JOptionPane.showMessageDialog(this, 
            "Booking functionality for '" + movie.getTitle() + "' will be implemented here!");
    }
      public void clearSearch() {
        if (searchtextfield != null) {
            searchtextfield.setText("");
        }
        if (allMovies != null) {
            filteredMovies = allMovies;
            displayMovies(allMovies);
        }
    }
        public void refreshMovies() {
        loadAllMovies();
    }
                                                       

      private void filterMoviesByCategory(String category) {
        try {
            if (movieController != null) {
                // You might need to add a method in MovieController to search by genre
                List<Movie> categoryMovies = movieController.searchMovies(""); // Get all movies first
                filteredMovies = categoryMovies.stream()
                    .filter(movie -> movie.getGenre().equalsIgnoreCase(category))
                    .collect(java.util.stream.Collectors.toList());
                displayMovies(filteredMovies);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error filtering by category: " + e.getMessage());
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton favouritebutton;
    private javax.swing.JComboBox<String> genres;
    private javax.swing.JButton historybutton;
    private javax.swing.JButton homebutton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel logo;
    private javax.swing.JButton logoutbuttton;
    private javax.swing.JButton moviebutton;
    private javax.swing.JPanel posterpanel;
    private javax.swing.JButton searchbutton;
    private javax.swing.JTextField searchtextfield;
    private javax.swing.JButton userbutton;
    private javax.swing.JButton watchlistbutton;
    // End of variables declaration//GEN-END:variables
}

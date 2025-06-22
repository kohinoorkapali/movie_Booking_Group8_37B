/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

// Add these imports
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
public class Dashboard extends javax.swing.JFrame {

    // Add these instance variables
    private MovieController movieController;
    private List<Movie> allMovies;
    private List<Movie> filteredMovies;
    private JPanel moviesDisplayPanel;

    /**
     * Creates new form Dashboard
     */
    public Dashboard() {
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
       
       private void setupMoviesDisplayArea() {
        // Create a panel to display movies - you might need to add this panel to your existing layout
        moviesDisplayPanel = new JPanel();
        moviesDisplayPanel.setLayout(new GridLayout(0, 3, 15, 15)); // 3 columns with spacing
        moviesDisplayPanel.setBackground(new Color(240, 240, 240));
        
        // If you have a specific panel in your GUI to display movies, replace this with that panel
        // For now, I'll assume you want to add it to jPanel1 (adjust as needed)
        if (jPanel1 != null) {
            jPanel1.setLayout(new BorderLayout());
            jPanel1.add(moviesDisplayPanel, BorderLayout.CENTER);
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
        if (searchtext != null) {
            searchtext.addKeyListener(new KeyListener() {
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
            searchtext.setToolTipText("Search movies by title...");
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
            
            String searchKeyword = searchtext.getText().trim();
            
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
        
        if (movies == null || movies.isEmpty()) {
            // Show "No movies" message
            JLabel noMoviesLabel = new JLabel("No movies to display", SwingConstants.CENTER);
            noMoviesLabel.setFont(new Font("Arial", Font.BOLD, 16));
            noMoviesLabel.setForeground(Color.GRAY);
            moviesDisplayPanel.add(noMoviesLabel);
        } else {
            // Display each movie
            for (Movie movie : movies) {
                JPanel movieCard = createMovieCard(movie);
                moviesDisplayPanel.add(movieCard);
            }
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

    // ✅ Load Poster Image
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

    // ✅ Movie title
    JLabel titleLabel = new JLabel("<html><center>" + movie.getTitle() + "</center></html>");
    titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
    titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
    titleLabel.setForeground(new Color(51, 51, 51));

    // ✅ Movie details panel
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

    // ✅ Add title and details to center area (under poster)
    JPanel centerPanel = new JPanel();
    centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
    centerPanel.setBackground(Color.WHITE);
    centerPanel.add(Box.createVerticalStrut(5));
    centerPanel.add(titleLabel);
    centerPanel.add(detailsPanel);

    card.add(centerPanel, BorderLayout.CENTER);

    // ✅ Hover and click effects
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
        if (searchtext != null) {
            searchtext.setText("");
        }
        if (allMovies != null) {
            filteredMovies = allMovies;
            displayMovies(allMovies);
        }
    }
        public void refreshMovies() {
        loadAllMovies();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    // Your existing initComponents() code stays here - don't modify it
    // </editor-fold>
  
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
   
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        searchtext = new javax.swing.JTextField();
        categoriescombobox = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(122, 106, 106));
        setMinimumSize(new java.awt.Dimension(940, 720));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(122, 106, 106));
        jPanel1.setAutoscrolls(true);
        jPanel1.setMinimumSize(new java.awt.Dimension(940, 720));
        jPanel1.setPreferredSize(new java.awt.Dimension(940, 720));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Dashboard");

        jButton1.setBackground(new java.awt.Color(122, 118, 132));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton1.setText("Home");

        jButton2.setBackground(new java.awt.Color(122, 115, 128));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton2.setText("Movies");

        jButton3.setBackground(new java.awt.Color(122, 114, 132));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setText("Favourite");

        jButton4.setBackground(new java.awt.Color(122, 114, 132));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton4.setText("Watchlist");

        jButton5.setBackground(new java.awt.Color(122, 114, 132));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton5.setText("History");

        searchtext.setBackground(new java.awt.Color(225, 221, 214));
        searchtext.setText("for search");

        categoriescombobox.setBackground(new java.awt.Color(122, 114, 132));
        categoriescombobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Categories", "All", "Action", "Comedy", "Drama", "Horror", "Fantasy", "Romance", "Thriller", "Sci-Fi", "Animation" }));
        categoriescombobox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoriescomboboxActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(122, 114, 132));
        jButton6.setBorderPainted(false);
        jButton6.setContentAreaFilled(false);
        jButton6.setFocusPainted(false);

        jLabel7.setIcon(new javax.swing.ImageIcon("C:\\Users\\nitan\\OneDrive\\Pictures\\Screenshots\\Screenshot 2025-04-29 120902.png")); // NOI18N
        jLabel7.setText("jLabel7");
        jLabel7.setPreferredSize(new java.awt.Dimension(128, 113));

        jButton7.setBackground(new java.awt.Color(225, 225, 225));
        jButton7.setBorderPainted(false);
        jButton7.setContentAreaFilled(false);
        jButton7.setFocusPainted(false);

        jButton8.setBackground(new java.awt.Color(122, 106, 106));
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/loupe1.png"))); // NOI18N

        jButton9.setBackground(new java.awt.Color(122, 106, 106));
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/user1.png"))); // NOI18N
        jButton9.setBorderPainted(false);

        jButton10.setBackground(new java.awt.Color(122, 114, 132));
        jButton10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/1828479.png"))); // NOI18N
        jButton10.setText("Log out");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(categoriescombobox, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                            .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(40, 40, 40)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(275, 275, 275)
                                        .addComponent(jLabel1))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(66, 66, 66)
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel4)
                        .addGap(27, 27, 27)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(searchtext, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(432, 432, 432))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(33, 33, 33)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(43, 43, 43))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(searchtext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(53, 53, 53))))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(77, 77, 77)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jButton1)
                                            .addComponent(jButton2))
                                        .addGap(34, 34, 34)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(categoriescombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(62, 62, 62)
                                .addComponent(jButton3)
                                .addGap(43, 43, 43)
                                .addComponent(jButton4)
                                .addGap(50, 50, 50)
                                .addComponent(jButton5)
                                .addGap(26, 26, 26)))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 154, Short.MAX_VALUE)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29))))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void categoriescomboboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoriescomboboxActionPerformed
        // TODO add your handling code here:
         String selectedCategory = (String) categoriescombobox.getSelectedItem();
        if (selectedCategory != null && !selectedCategory.equals("Categories")) {
            filterMoviesByCategory(selectedCategory);
        }
    }//GEN-LAST:event_categoriescomboboxActionPerformed

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
    private javax.swing.JComboBox<String> categoriescombobox;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField searchtext;
    // End of variables declaration//GEN-END:variables

}

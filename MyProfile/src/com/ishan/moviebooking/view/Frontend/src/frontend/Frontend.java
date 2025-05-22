/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package frontend;

/**
 *
 * @author regmi
 */

import javax.swing.*;
import java.awt.*;

public class Frontend extends JFrame {

    public Frontend() {
        setTitle("User Profile - Movie Booking App");
        setSize(500, 650); // Made dashboard bigger
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Optional Look and Feel (more modern look)
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            System.out.println("Nimbus Look and Feel not available.");
        }

        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        mainPanel.setBackground(Color.WHITE);

        // Profile Picture
        JLabel profilePic = new JLabel();
        profilePic.setIcon(new ImageIcon(
                new ImageIcon("default_profile.png")
                        .getImage().getScaledInstance(140, 140, Image.SCALE_SMOOTH)));
        profilePic.setAlignmentX(Component.CENTER_ALIGNMENT);
        profilePic.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        mainPanel.add(profilePic);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 25)));

        // Name and Phone
        JLabel nameLabel = new JLabel("Name: Mr Ishan");
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel phoneLabel = new JLabel("Phone: +977-98XXXXXXXX");
        phoneLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        phoneLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(nameLabel);
        mainPanel.add(phoneLabel);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));

        // Buttons
        String[] buttonLabels = {
            "Edit Profile",
            "View Booking History",
            "Upcoming Bookings",
            "Preferences"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setMaximumSize(new Dimension(300, 45));  // Wider buttons
            button.setFont(new Font("SansSerif", Font.PLAIN, 16));
            button.setFocusPainted(false);
            mainPanel.add(button);
            mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        }

        add(mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Frontend().setVisible(true);
        });
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class EditProfileForm extends JFrame {

    private JTextField emailField;
    private JTextField usernameField;
    private JButton saveButton;

    private profile dashboard;  // Optional reference to main dashboard

    // No-arg constructor calls main constructor with null dashboard
    public EditProfileForm() {
        this(null);
    }

    // Main constructor with dashboard reference
    public EditProfileForm(profile dashboard) {
        this.dashboard = dashboard;
        setTitle("Edit Profile");
        initComponents();
        setSize(350, 180);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void initComponents() {
        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField(20);
        emailField.setEnabled(false);  // email is usually not editable

        JLabel usernameLabel = new JLabel("New Username:");
        usernameField = new JTextField(20);

        saveButton = new JButton("Save");

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(new JLabel()); // empty cell
        panel.add(saveButton);

        add(panel);
    }

    // Set email value (called from controller)
    public void setEmail(String email) {
        emailField.setText(email);
    }

    // Get email (if ever needed)
    public String getEmail() {
        return emailField.getText();
    }

    // Set username value (called from controller)
    public void setUsername(String username) {
        usernameField.setText(username);
    }

    // Get username input from user
    public String getUsername() {
        return usernameField.getText();
    }

    // Allow controller to attach Save button listener
    public void addSaveListener(ActionListener listener) {
        saveButton.addActionListener(listener);
    }
}
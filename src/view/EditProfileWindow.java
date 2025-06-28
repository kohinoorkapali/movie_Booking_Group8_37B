/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class EditProfileWindow extends JFrame {

    private JTextField usernameField;
    private JButton saveButton;

    // Functional interface to pass update logic from controller
    public interface UsernameUpdateCallback {
        void onUsernameUpdated(String newUsername);
    }

    public EditProfileWindow(String currentUsername, UsernameUpdateCallback callback) {
        setTitle("Edit Profile");
        setSize(400, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JLabel label = new JLabel("Enter new username:");
        usernameField = new JTextField(currentUsername, 20);
        saveButton = new JButton("Save");

        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(label);
        inputPanel.add(usernameField);

        add(inputPanel, BorderLayout.CENTER);
        add(saveButton, BorderLayout.SOUTH);

        // Action listener for save
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newUsername = usernameField.getText().trim();
        if (!newUsername.isEmpty()) {
            callback.onUsernameUpdated(newUsername);  // Let controller decide what to show
            dispose(); // close the window
        } else {
            JOptionPane.showMessageDialog(EditProfileWindow.this, "Username cannot be empty!");
        }
    }
        });
    }
}

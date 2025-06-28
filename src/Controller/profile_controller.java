/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Dao.profile_dao;
import Model.ProfileModel;
import view.EditProfileWindow;
import view.Profile;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class profile_controller {

    private Profile profileView;
    private profile_dao dao;
    private int userId;  // The logged-in user's ID

    // Constructor requires the userId
    public profile_controller(Profile profileView, int userId) {
        this.profileView = profileView;
        this.dao = new profile_dao();
        this.userId = userId;

        // Register listener for Edit Profile button
        this.profileView.addEditProfileListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openEditProfileWindow();
            }
        });
    }

    // Load username from DB and show in Profile view
    public void loadUsername() {
        ProfileModel model = dao.getProfile(userId);
        if (model != null) {
            profileView.setUsernameText(model.getUsername());
        } else {
            profileView.setUsernameText("Guest");
        }
    }

    // Opens the EditProfileWindow with current username
    private void openEditProfileWindow() {
        ProfileModel currentProfile = dao.getProfile(userId);
        if (currentProfile == null) {
            JOptionPane.showMessageDialog(profileView, "Failed to load profile data.");
            return;
        }

        EditProfileWindow editWindow = new EditProfileWindow(currentProfile.getUsername(), newUsername -> {
    if (newUsername == null || newUsername.trim().isEmpty()) {
        JOptionPane.showMessageDialog(profileView, "Username cannot be empty.");
        return;
    }

    newUsername = newUsername.trim();

    if (dao.isUsernameTaken(newUsername, userId)) {
        JOptionPane.showMessageDialog(profileView, "Username already taken, please choose another.");
        return;
    }

    ProfileModel updatedModel = new ProfileModel(userId, newUsername);
    boolean success = dao.updateProfile(updatedModel);

    if (success) {
        profileView.setUsernameText(newUsername);
        JOptionPane.showMessageDialog(profileView, "Username updated successfully.");
    } else {
        JOptionPane.showMessageDialog(profileView, "Failed to update username.");
    }
});


        editWindow.setVisible(true);
    }
}
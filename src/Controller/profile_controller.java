/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Dao.ProfileDAO;
import Model.Profile_ishan;
import view.profile;
import view.EditProfileForm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class profile_controller {

    private profile dashboardView;
    private Profile_ishan model;
    private ProfileDAO dao;

    public profile_controller(profile dashboardView) {
        this.dashboardView = dashboardView;
        this.dao = new ProfileDAO();
        
        // Load initial profile data from DB by ID (assuming ID=1 here)
        model = dao.getProfileById(1);
        if (model == null) {
            // If profile not found, create a default one
            model = new Profile_ishan(1, "DefaultUser", "default@mail.com");
        }

        // Set initial username on dashboard
        dashboardView.setUsername(model.getUsername());

        initController();
    }

    private void initController() {
        dashboardView.addEditProfileListener(e -> openEditProfileForm());
    }

    private void openEditProfileForm() {
        EditProfileForm editForm = new EditProfileForm(dashboardView); //cannot be applied to given types

        // Pre-fill form fields with data from model
        editForm.setEmail(model.getEmail());
        editForm.setUsername(model.getUsername());

        // Attach Save button listener in edit form
        editForm.addSaveListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newUsername = editForm.getUsername();
                String newEmail = editForm.getEmail();

                // Basic validation
                if (newUsername.isEmpty() || newEmail.isEmpty()) {
                    dashboardView.showMessage("Username or Email cannot be empty.");
                    return;
                }

                // Update model
                model.setUsername(newUsername);
                model.setEmail(newEmail);

                // Update DB through DAO
                boolean success = dao.updateUsername(model);

                if (success) {
                    dashboardView.showMessage("Profile updated successfully!");
                    dashboardView.setUsername(newUsername);
                    editForm.dispose();
                } else {
                    dashboardView.showMessage("Failed to update profile.");
                }
            }
        });

        editForm.setVisible(true);
    }
}

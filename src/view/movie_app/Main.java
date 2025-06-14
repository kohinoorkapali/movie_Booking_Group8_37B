/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.movie_app;

import Controller.profile_controller;
import view.profile;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Create dashboard view
            profile dashboardView = new profile();

            // Create controller and link view & model
            profile_controller controller = new profile_controller(dashboardView);

            // Show the dashboard window
            dashboardView.setVisible(true);
        });
    }
}

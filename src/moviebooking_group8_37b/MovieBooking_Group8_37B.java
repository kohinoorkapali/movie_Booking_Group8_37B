/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package moviebooking_group8_37b;

import Database.*;
import Database.MySqlConnection;

import Dao.loginpagedao;
import Controller.loginpageController;
import view.loginPage2;
/**
 *
 * @author Kohinoor
 */
public class MovieBooking_Group8_37B {

    public static void main(String[] args) {
        // Open DB connection
        Database db = new MySqlConnection();

        if (db.openConnection() != null) {
            System.out.println("Database connected successfully");

            // ✅ Move everything inside here
            loginPage2 loginView = new loginPage2();
            loginpagedao loginDao = new loginpagedao();
            loginpageController logController = new loginpageController(loginView, loginDao);

            // If you have a method in controller called open(), call it here
            // But if not, show the view directly
            loginView.setVisible(true); // OR logController.open();
        } else {
            System.out.println("Database connection failed");
        }
    }
}



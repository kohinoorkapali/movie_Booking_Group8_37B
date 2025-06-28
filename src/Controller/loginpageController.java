/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import view.Dashboard;
import Model.loginpage;
import Dao.loginpagedao;
import view.loginPage2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class loginpageController {
    
    private loginPage2 view;
    private loginpagedao Dao;

    public loginpageController(loginPage2 view, loginpagedao dao) {
        this.view = view;
        this.Dao = dao;

        // Add action listener to login button
        view.addLoginListener(new LoginListener());
    }
    public void open() {
        this.view.setVisible(true);
    }

   class LoginListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        String username = view.getUsernameField().getText();
        String password = new String(view.getPasswordField().getPassword());

        loginpage user = new loginpage(username, password);

        // 👇 Get user ID instead of just checking boolean
        Integer userId = Dao.loginAndGetUserId(user);

        if (userId != null) {
            JOptionPane.showMessageDialog(view, "Login Successful!");

            // 👇 Pass userId to Dashboard
            new Dashboard(userId).setVisible(true);

            view.dispose();
        } else {
            JOptionPane.showMessageDialog(view, "Invalid username or password.");
        }
    }
}
}
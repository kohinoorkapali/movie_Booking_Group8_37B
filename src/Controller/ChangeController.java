/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import javax.swing.JOptionPane;

import Dao.ChangePasswordDao;
import view.ChangePassword;

/**
 *
 * @author Kohinoor
 */
public class ChangeController {
    private ChangePassword view;
    private ChangePasswordDao userDao;
    private int userId;

    public ChangeController(ChangePassword view, int userId) {
        this.view = view;
        this.userDao = new ChangePasswordDao();
        this.userId = userId;

        view.getChangePasswordButton().addActionListener(e -> changePassword());
    }

    private void changePassword() {
        String oldPwd = new String(view.getOldPasswordField().getPassword());
        String newPwd = new String(view.getNewPasswordField().getPassword());

        if (oldPwd.isEmpty() || newPwd.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Fields cannot be empty.");
            return;
        }

        if (!userDao.verifyCurrentPassword(userId, oldPwd)) {
            JOptionPane.showMessageDialog(view, "Current password is incorrect.");
            return;
        }

        boolean success = userDao.changePassword(userId, newPwd);
        if (success) {
            JOptionPane.showMessageDialog(view, "Password updated successfully!");
            view.dispose();
        } else {
            JOptionPane.showMessageDialog(view, "Failed to update password.");
        }
    }
}

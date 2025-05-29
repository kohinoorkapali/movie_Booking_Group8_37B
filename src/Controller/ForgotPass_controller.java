/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import Dao.UserDao;
/**
 *
 * @author Kohinoor
 */
public class ForgotPass_controller {


    private final UserDao userDao;

    public ForgotPass_controller() {
        userDao = new UserDao();
    }

    // Check if the email exists in DB
    public boolean checkEmailExists(String email) {
        return userDao.emailExists(email);
    }

    // Verify security question answers
    public boolean verifySecurityAnswers(String email, String answer1, String answer2) {
        return userDao.validateSecurityAnswers(email, answer1, answer2);
    }

    // Reset the password for the user
    public boolean resetPassword(String email, String newPassword) {
        return userDao.resetPassword(email, newPassword);
    }


}

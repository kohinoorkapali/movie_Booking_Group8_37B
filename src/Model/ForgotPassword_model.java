/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Kohinoor
 */
public class ForgotPassword_model {

    private String email;
    private String securityAnswer1;
    private String securityAnswer2;
    private String password; // Optional: you may not store this here depending on your app's architecture

    public ForgotPassword_model(String email, String securityAnswer1, String securityAnswer2, String password) {
        this.email = email;
        this.securityAnswer1 = securityAnswer1;
        this.securityAnswer2 = securityAnswer2;
        this.password = password;
    }

    // Getters
    public String getEmail() {
        return email;
    }

    public String getSecurityAnswer1() {
        return securityAnswer1;
    }

    public String getSecurityAnswer2() {
        return securityAnswer2;
    }

    public String getPassword() {
        return password;
    }

    // Setters
    public void setEmail(String email) {
        this.email = email;
    }

    public void setSecurityAnswer1(String securityAnswer1) {
        this.securityAnswer1 = securityAnswer1;
    }

    public void setSecurityAnswer2(String securityAnswer2) {
        this.securityAnswer2 = securityAnswer2;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

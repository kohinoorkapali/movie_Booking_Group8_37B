/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Kohinoor
 */
public class Security_SignUp {
    private String email;
    private String answer1;
    private String answer2;

    public Security_SignUp(String email, String answer1, String answer2) {
        this.email = email;
        this.answer1 = answer1;
        this.answer2 = answer2;
    }

    public String getEmail() {
        return email;
    }

    public String getAnswer1() {
        return answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }
}

package Model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.security.Security;

/**
 *
 * @author Nishan
 */
public class SignUp1 {
    private int id;
    private String username;
    private String email;
    private String password;
   

    
    public SignUp1(String username,String email,String password){
        this.username =username;
        this.email =email;
        this.password = password;
    }

    
    public int getUserId() {
        return id;
    }
    public void setUserId(int id) {
        this.id = id;
    }
    
    public String getUserName(){
        return username;
    }
    
    public void setUserName(String username){
        this.username=username;
    }
    
    public String getUserEmail(){
        return email;
    }
    
    public void setUserEmail(String email){
        this.email = email;
    }
    
    public String getUserPassword(){
        return password;
    }
    
    public void setUserPassword(String password){
        this.password = password;
    }

private Security_SignUp securityAnswers;

public void setSecurityAnswers(Security_SignUp securityAnswers) {
    this.securityAnswers = securityAnswers;
}

public Security_SignUp getSecurityAnswers() {
    return securityAnswers;
}
}

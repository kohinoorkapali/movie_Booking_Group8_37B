/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author nitan
 */
public class loginpage {
    private String username;
    private String password;
    
    public loginpage() {}//so we can use username and password in login method in  controller.
    
    public  loginpage(String username, String password){
    this.username = username;
    this.password = password;
    }
    
    //getter and setter for username
    public String getUsername(){
    return this.username;
    }
   public void setUsername(String username){
    this.username = username;
}
    
    //getter and setter for password
    public String getPassword(){
    return this.password;
    }
public void setPassword(String password){
    this.password = password;
}
}
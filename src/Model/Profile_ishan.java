/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

public class Profile_ishan {

    private int id;
    private String username;
    private String email;

    // Default constructor
    public Profile_ishan() {
    }

    // Parameterized constructor (optional)
    public Profile_ishan(int id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    // Getter and Setter methods

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

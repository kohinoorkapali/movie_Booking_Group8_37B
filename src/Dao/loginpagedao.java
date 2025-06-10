/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Database.*;
import Model.loginpage;


public class loginpagedao {
    
    MySqlConnection connection = new MySqlConnection();



   public boolean login(loginpage user) {
    boolean isValid = false;
    Connection conn = connection.openConnection();

    String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, user.getUsername());
        stmt.setString(2, user.getPassword());

        ResultSet rs = stmt.executeQuery();

        // Check if a record exists
        if (rs.next()) {
            isValid = true;
        }

        rs.close();
    } catch (Exception e) {
        e.printStackTrace();
    }

    return isValid;
}

}

   

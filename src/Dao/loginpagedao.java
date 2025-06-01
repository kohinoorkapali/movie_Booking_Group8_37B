/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Database.*;
import Model.loginpage;


public class loginpagedao {
    
    MySqlConnection connection = new MySqlConnection();

    public int login(loginpage user) {
        int userId = -1;
        
        Connection conn = connection.openConnection();

        try {


            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                userId = rs.getInt("id"); // If you have an ID column in your table
                // If you don't have an ID column, you can return 1 instead
                // userId = 1;
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return userId;
    }
}
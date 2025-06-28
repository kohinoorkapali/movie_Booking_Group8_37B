package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Database.MySqlConnection;
import Model.loginpage;

public class loginpagedao {

    MySqlConnection connection = new MySqlConnection();

    // Return user_id instead of just true/false
    public Integer loginAndGetUserId(loginpage user) {
    Integer userId = null;
    Connection conn = connection.openConnection();

    String sql = "SELECT user_id FROM users WHERE username = ? AND password = ?";

    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, user.getUsername().trim());
        stmt.setString(2, user.getPassword().trim());

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            userId = rs.getInt("user_id");
        }

        rs.close();
    } catch (Exception e) {
        e.printStackTrace();
    }

    return userId;
}

}
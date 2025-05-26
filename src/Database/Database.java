/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;
import java.sql.*;

/**
 *
 * @author Kohinoor
 */
public interface Database {
    Connection openConnection();
    void closeConnection(Connection conn);
    ResultSet runQuesry(Connection conn,String query);
    int executeUpdate(Connection conn,String query);
}


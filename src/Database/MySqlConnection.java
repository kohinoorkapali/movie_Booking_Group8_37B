/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;


import java.sql.*;

public class MySqlConnection implements Database{
    @Override
    public Connection openConnection() {
        try {
            String username = "root";
            String password = "nitantghimire";
            String db = "project";
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/?user=root"+db,username,password);
            if (connection == null) {
                System.out.println("Database connection fail");
            } else {
                System.out.println("Database connection successful");

            }
            return connection;
        } catch (SQLException e) {
            System.out.println(e);
            return null;

        }
        
      }

    @Override
    public void closeConnection(Connection conn) {
        try{
        if(conn!=null&&!conn.isClosed()){
        conn.close();
        System.out.println("Connection close");
        }
        }catch(SQLException e){
        System.out.println(e);
        }

    }

    @Override
    public ResultSet runQuery(Connection conn, String query) {
        try{
        Statement stmp = conn.createStatement();
        ResultSet result = stmp.executeQuery(query);
        return  result;
        }catch(SQLException e){
        System.out.println(e);
        return null;
        }

    }

    @Override
    public int executeUpdate(Connection conn, String query) {
        try{
        Statement stmp = conn.createStatement();
        int result = stmp.executeUpdate(query);
        return result;
        }catch(SQLException e){
        System.out.println(e);
        return -1;
        }

    }
}

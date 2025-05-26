/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package moviebooking_group8_37b;

import Database.*;
import Database.MySqlConnection;

/**
 *
 * @author Kohinoor
 */
public class MovieBooking_Group8_37B {
        public static void main(String[] args){
        Database db = new MySqlConnection();
        System.out.println(db);
        try {
            db.openConnection();
            System.out.println(db.openConnection());
//            if (db.openConnection() != null){
//                System.out.println("Database  connected successfully!");
//               }else{
//                System.out.println("Failed to connect to database.");
//            }
        } catch(Exception e){
           System.out.println("Failed to connect to database.");
           System.out.println(e);
       }
    }
    
}

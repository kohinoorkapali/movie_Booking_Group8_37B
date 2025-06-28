/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Database.MySqlConnection;
import java.sql.*;
import java.util.*;

import Model.SeatModel;



public class seat_dao {

       private MySqlConnection mySqlConnection = new MySqlConnection();

    public Map<String, SeatModel> getAllBookings(int movieId) {
    Map<String, SeatModel> seatMap = new HashMap<>();
    String sql = "SELECT movie_id, seat_number, booked_by_username FROM seat_details WHERE movie_id = ?";

    Connection con = mySqlConnection.openConnection();
    if (con == null) return seatMap;

    try (PreparedStatement pst = con.prepareStatement(sql)) {
        pst.setInt(1, movieId);
        try (ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                String seatNumber = rs.getString("seat_number");
                String username = rs.getString("booked_by_username");

                SeatModel seat = new SeatModel(movieId, seatNumber, username);
                seatMap.put(seatNumber, seat);
            }
        }
    } catch (SQLException e) {
        System.out.println("Error loading bookings for movieId " + movieId + ": " + e.getMessage());
    } finally {
        mySqlConnection.closeConnection(con);
    }

    return seatMap;
}

        public boolean bookSeat(int movieId, String seatNumber, String username) {
        String sql = "UPDATE seat_details SET booked_by_username = ? WHERE movie_id = ? AND seat_number = ?";
        Connection con = mySqlConnection.openConnection();
        if (con == null) return false;

        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, username);
            pst.setInt(2, movieId);
            pst.setString(3, seatNumber);

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Failed to book: " + e.getMessage());
            return false;
        } finally {
            mySqlConnection.closeConnection(con);
        }
    }

   public boolean cancelSeat(int movieId, String seatNumber) {
        String sql = "UPDATE seat_details SET booked_by_username = NULL WHERE movie_id = ? AND seat_number = ?";
        Connection con = mySqlConnection.openConnection();
        if (con == null) return false;

        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, movieId);
            pst.setString(2, seatNumber);

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Failed to cancel: " + e.getMessage());
            return false;
        } finally {
            mySqlConnection.closeConnection(con);
        }
    }

public SeatModel getBookingBySeat(int movieId, String seatNumber) {
        String sql = "SELECT booked_by_username FROM seat_details WHERE movie_id = ? AND seat_number = ?";
        Connection con = mySqlConnection.openConnection();
        if (con == null) return null;

        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, movieId);
            pst.setString(2, seatNumber);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    String username = rs.getString("booked_by_username");
                    return new SeatModel(movieId, seatNumber, username);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error getting seat: " + e.getMessage());
        } finally {
            mySqlConnection.closeConnection(con);
        }

        return null;
    }

    public boolean initializeSeatsForMovie(int movieId, List<String> seatNumbers) {
    if (seatNumbers == null || seatNumbers.isEmpty()) {
        System.out.println("No seat numbers provided for initialization.");
        return false; // or throw an IllegalArgumentException
    }

    String sql = "INSERT IGNORE INTO seat_details (movie_id, seat_number) VALUES (?, ?)";
    Connection con = mySqlConnection.openConnection();
    if (con == null) {
        System.out.println("Failed to open database connection.");
        return false;
    }

    try (PreparedStatement pst = con.prepareStatement(sql)) {
        // Disable auto-commit for transaction management
        con.setAutoCommit(false);
        
        for (String seatNum : seatNumbers) {
            pst.setInt(1, movieId);
            pst.setString(2, seatNum);
            pst.addBatch();
        }
        
        int[] results = pst.executeBatch();
        con.commit(); // Commit the transaction

        // Check if any insert failed
        for (int result : results) {
            if (result == Statement.EXECUTE_FAILED) {
                System.out.println("Failed to insert one or more seat numbers.");
                return false;
            }
        }
        
        return true; // All inserts were successful
    } catch (SQLException e) {
        System.out.println("Error initializing seats: " + e.getMessage());
        try {
            con.rollback(); // Rollback in case of error
        } catch (SQLException rollbackEx) {
            System.out.println("Error during rollback: " + rollbackEx.getMessage());
        }
        return false;
    } finally {
        mySqlConnection.closeConnection(con);
    }
}


}
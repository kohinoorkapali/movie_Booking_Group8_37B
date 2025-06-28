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
    String sql = "SELECT movie_id, seat_number, booked_by_user_id, booked_for_name FROM seat_details WHERE movie_id = ?";

    Connection con = mySqlConnection.openConnection();
    if (con == null) return seatMap;

    try (PreparedStatement pst = con.prepareStatement(sql)) {
        pst.setInt(1, movieId);
        try (ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                String seatNumber = rs.getString("seat_number");
                Integer bookedByUserId = rs.getObject("booked_by_user_id") != null ? rs.getInt("booked_by_user_id") : null;
                String bookedFor = rs.getString("booked_for_name");

                SeatModel seat = new SeatModel(movieId, seatNumber, bookedByUserId, bookedFor);
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

public boolean bookSeat(int movieId, String seatNumber, Integer userId, String bookedForName) {
    String sql = "UPDATE seat_details SET booked_by_user_id = ?, booked_for_name = ? WHERE movie_id = ? AND seat_number = ?";
    Connection con = mySqlConnection.openConnection();
    if (con == null) return false;

    try (PreparedStatement pst = con.prepareStatement(sql)) {
        if (userId != null) {
            pst.setInt(1, userId);
        } else {
            pst.setNull(1, java.sql.Types.INTEGER);
        }
        pst.setString(2, bookedForName);
        pst.setInt(3, movieId);
        pst.setString(4, seatNumber);

        return pst.executeUpdate() > 0;

    } catch (SQLException e) {
        System.out.println("Failed to book: " + e.getMessage());
        return false;
    } finally {
        mySqlConnection.closeConnection(con);
    }
}

public boolean cancelSeat(int movieId, String seatNumber) {
    String sql = "UPDATE seat_details SET booked_by_user_id = NULL, booked_for_name = NULL WHERE movie_id = ? AND seat_number = ?";
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
    String sql = "SELECT booked_by_user_id, booked_for_name FROM seat_details WHERE movie_id = ? AND seat_number = ?";
    Connection con = mySqlConnection.openConnection();
    if (con == null) return null;

    try (PreparedStatement pst = con.prepareStatement(sql)) {
        pst.setInt(1, movieId);
        pst.setString(2, seatNumber);

        try (ResultSet rs = pst.executeQuery()) {
            if (rs.next()) {
                Integer bookedByUserId = rs.getObject("booked_by_user_id") != null ? rs.getInt("booked_by_user_id") : null;
                String bookedFor = rs.getString("booked_for_name");
                return new SeatModel(movieId, seatNumber, bookedByUserId, bookedFor);
            }
        }

    } catch (SQLException e) {
        System.out.println("Error getting seat: " + e.getMessage());
    } finally {
        mySqlConnection.closeConnection(con);
    }

    return null;
}

public void initializeSeatsForMovie(int movieId, List<String> seatNumbers) {
    String sql = "INSERT IGNORE INTO seat_details (movie_id, seat_number) VALUES (?, ?)";
    Connection con = mySqlConnection.openConnection();
    if (con == null) return;

    try (PreparedStatement pst = con.prepareStatement(sql)) {
        for (String seatNumber : seatNumbers) {
            pst.setInt(1, movieId);
            pst.setString(2, seatNumber);
            pst.addBatch();
        }
        pst.executeBatch();
    } catch (SQLException e) {
        System.out.println("Failed to initialize seats: " + e.getMessage());
    } finally {
        mySqlConnection.closeConnection(con);
    }
}
}
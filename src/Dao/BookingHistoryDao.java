/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;
import Database.MySqlConnection;
import Model.BookingHistoryModel;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author Kohinoor
 */
public class BookingHistoryDao {
  
    private MySqlConnection mySqlConnection = new MySqlConnection();

    public ArrayList<BookingHistoryModel> getBookingsByUser(int userId) {
        ArrayList<BookingHistoryModel> bookings = new ArrayList<>();

        String query = "SELECT sd.seat_id, p.movie_title, sd.seat_number, sd.booked_for_name, " +
                       "p.total_price, p.payment_method, p.status, p.payment_date " +
                       "FROM seat_details sd " +
                       "JOIN payments p ON p.user_id = sd.booked_by_user_id AND p.movie_title = " +
                       "(SELECT title FROM movies WHERE id = sd.movie_id) " +
                       "WHERE sd.booked_by_user_id = ?";

        Connection conn = mySqlConnection.openConnection();
        if (conn == null) {
            System.out.println("Failed to open connection in getBookingsByUser");
            return bookings; // empty list
        }

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int seatId = rs.getInt("seat_id");
                String movieTitle = rs.getString("movie_title");
                String seatNumber = rs.getString("seat_number");
                String bookedFor = rs.getString("booked_for_name");
                double totalPrice = rs.getDouble("total_price");
                String paymentMethod = rs.getString("payment_method");
                String status = rs.getString("status");

                Timestamp paymentTimestamp = rs.getTimestamp("payment_date");
                String bookingDate = "";
                String bookingTime = "";

                if (paymentTimestamp != null) {
                    bookingDate = paymentTimestamp.toLocalDateTime().toLocalDate().toString();
                    bookingTime = paymentTimestamp.toLocalDateTime().toLocalTime().toString();
                }

                // Adjust your BookingHistoryModel constructor to accept bookingDate and bookingTime
                BookingHistoryModel booking = new BookingHistoryModel(
                    seatId, movieTitle, seatNumber, bookedFor,
                    totalPrice, paymentMethod, status,
                    bookingDate, bookingTime
                );

                bookings.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mySqlConnection.closeConnection(conn);
        }

        return bookings;
    }

    public boolean deleteBooking(int seatId) {
        String deleteSeatQuery = "DELETE FROM seat_details WHERE seat_id = ?";

        Connection conn = mySqlConnection.openConnection();
        if (conn == null) {
            System.out.println("Failed to open connection in deleteBooking");
            return false;
        }

        try (PreparedStatement ps = conn.prepareStatement(deleteSeatQuery)) {
            ps.setInt(1, seatId);
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            mySqlConnection.closeConnection(conn);
        }
    }
}
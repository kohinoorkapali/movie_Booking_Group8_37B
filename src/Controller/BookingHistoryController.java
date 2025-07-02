/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.util.ArrayList;

import Dao.BookingHistoryDao;
import Model.BookingHistoryModel;

/**
 *
 * @author Kohinoor
 */
public class BookingHistoryController {
     private BookingHistoryDao dao = new BookingHistoryDao();

    public ArrayList<BookingHistoryModel> fetchBookings(int userId) {
        return dao.getBookingsByUser(userId);
    }

    public boolean deleteBooking(int seatId) {
        return dao.deleteBooking(seatId);
    }
}
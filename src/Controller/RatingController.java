/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import Dao.RatingDAO;
import model.RatingModel;

import java.sql.Connection;

public class RatingController {

    private final RatingDAO dao;

    public RatingController(Connection conn) {
        this.dao = new RatingDAO(conn);
    }

    public boolean submitRating(int movieId, int userId, int ratingValue) {
        RatingModel rating = new RatingModel(movieId, userId, ratingValue);
        return dao.saveOrUpdateRating(rating);
    }
}

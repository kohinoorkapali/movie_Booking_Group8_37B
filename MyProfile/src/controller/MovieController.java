/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import Rough_model.model;
import Movie_data.DatabaseManager;

import java.sql.SQLException;
import java.util.List;

public class MovieController {
    private final DatabaseManager dbManager;

    public MovieController() {
        dbManager = new DatabaseManager();
    }

    public List<model> getMovies() throws SQLException, ClassNotFoundException {
        return dbManager.fetchMovies();  // Your DatabaseManager method to fetch movies from DB
    }
}

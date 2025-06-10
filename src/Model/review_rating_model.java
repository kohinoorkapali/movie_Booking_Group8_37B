/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

public class review_rating_model {

    private String movieName;
    private String userRating; // Poor, Avg, Good, V.Good, Excellent
    private String reviewText; // Optional user comments

    public review_rating_model() {
        this.movieName = "";
        this.userRating = "";
        this.reviewText = "";
    }

    public review_rating_model(String movieName, String userRating, String reviewText) {
        this.movieName = movieName;
        this.userRating = userRating;
        this.reviewText = reviewText;
    }

    // Getters
    public String getMovieName() {
        return movieName;
    }

    public String getUserRating() {
        return userRating;
    }

    public String getReviewText() {
        return reviewText;
    }

    // Setters
    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public void setUserRating(String userRating) {
        this.userRating = userRating;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    // Example business logic (optional)
    public boolean isRatingValid() {
        return userRating.equals("Poor") || userRating.equals("Avg") || userRating.equals("Good")
                || userRating.equals("V.Good") || userRating.equals("Excellent");
    }

    @Override
    public String toString() {
        return "Movie: " + movieName + ", Rating: " + userRating + ", Review: " + reviewText;
    }
}

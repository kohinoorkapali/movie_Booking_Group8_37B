/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Dao.ReviewDa;
import Dao.loginpagedao;
import Model.ProfileModel;
import Model.loginpage;
import javax.swing.JOptionPane;
import view.Review;

/**
 *
 * @author Nishan
 */
public class ReviewController {
//    public void submitRatingAndComment( loginpage user, int selectedRating,String comment, Review view) {
//         
//        
//        System.out.println("User ID: " + (user != null ? user.getUserId() : "null"));
//        System.out.println("Selected Rating: " + selectedRating);
//        if (user != null && selectedRating > 0 ) {
//            ReviewDa dao = new ReviewDa();
//            
////             if (dao.hasUserRatedGuide(guide.getGuideId(), user.getUserId())) {
////                JOptionPane.showMessageDialog(view, "You have already rated this guide.");
////                return;
////            }
//            boolean success = dao.submitRating(user.getUserId(), selectedRating, comment );
//
//            if (success) {
//                JOptionPane.showMessageDialog(view, "Thanks for rating!");
//                
//            } else {
//                JOptionPane.showMessageDialog(view, "Failed to submit rating.");
//            }
//        } else {
//            JOptionPane.showMessageDialog(view, "Please select a star before submitting.");
//        }
//    }
    
    public void submitRatingAndComment( int userId, int selectedRating,String comment, Review view) {
         
        
        System.out.println("User ID: " + userId);
        System.out.println("Selected Rating: " + selectedRating);
        if (userId > 0 && selectedRating > 0 ) {
            ReviewDa dao = new ReviewDa();
            
//             if (dao.hasUserRatedGuide(guide.getGuideId(), user.getUserId())) {
//                JOptionPane.showMessageDialog(view, "You have already rated this guide.");
//                return;
//            }
            boolean success = dao.submitRating(userId, selectedRating, comment );

            if (success) {
                JOptionPane.showMessageDialog(view, "Thanks for rating!");
                
            } else {
                JOptionPane.showMessageDialog(view, "Failed to submit rating.");
            }
        } else {
            JOptionPane.showMessageDialog(view, "Please select a star before submitting.");
        }
    }
}

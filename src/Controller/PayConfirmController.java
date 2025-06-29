/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Dao.PayConfirmDao;
import Model.PayConfirmModel;

/**
 *
 * @author Kohinoor
 */
public class PayConfirmController {
    private PayConfirmDao dao = new PayConfirmDao();

    public PayConfirmModel getConfirmationDetails(int paymentId) {
        return dao.fetchConfirmation(paymentId);
    }

}

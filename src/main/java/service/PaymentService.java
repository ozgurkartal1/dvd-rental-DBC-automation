package service;

import mapper.PaymentMapper;
import model.Payment;
import utils.DBUtils;

import java.util.List;

public class PaymentService {

    public List<Payment> getAllPayments(){
        String query = "SELECT * FROM payment";
        return DBUtils.executeQuery(query , new PaymentMapper());
    }

    public Payment getPaymentById(int paymentId){
        String query = "SELECT * FROM payment WHERE payment_id IN(" + paymentId + ")";
        return DBUtils.executeQuery(query , new PaymentMapper()).get(0);
    }

    public List<Payment> getPaymentByIds(List<Integer> paymentIds){
        String query = "SELECT * FROM payment WHERE payment_id IN(";
        for (int i = 0; i < paymentIds.size(); i++) {
            if(i == paymentIds.size() - 1){
                query += paymentIds.get(i) + ")";
            }else{
                query += paymentIds.get(i) + ", ";
            }
        }
        return DBUtils.executeQuery(query , new PaymentMapper());
    }


    public List<Payment> getCountOfPaymentForDifferentMonth(){
        String query = "SELECT TO_CHAR(payment_date, 'Month') AS month, COUNT(payment_id)" +
                " FROM payment" +
                " GROUP BY TO_CHAR(payment_date, 'Month')" +
                " ORDER BY COUNT(payment_id) DESC";
        return DBUtils.executeQuery(query , new PaymentMapper());
    }
}

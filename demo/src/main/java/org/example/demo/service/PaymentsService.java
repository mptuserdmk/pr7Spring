package org.example.demo.service;

import org.example.demo.model.PaymentsModel;
import java.util.List;

public interface PaymentsService {
    List<PaymentsModel> findAll();
    PaymentsModel findById(int id);
    PaymentsModel addPayment(PaymentsModel payment);
    PaymentsModel updatePayment(PaymentsModel payment);
    void deletePayment(int id);
}
package org.example.demo.repository;

import org.example.demo.model.PaymentsModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class PaymentsRepository {

    private List<PaymentsModel> payments = new ArrayList<>();
    private AtomicInteger idCounter = new AtomicInteger(1);

    public PaymentsRepository() {
        payments.add(new PaymentsModel(idCounter.getAndIncrement(), 1, "2024-01-01", 1500.0, "Card"));
        payments.add(new PaymentsModel(idCounter.getAndIncrement(), 2, "2024-02-01", 2500.0, "Cash"));
    }

    public List<PaymentsModel> findAll() {
        return new ArrayList<>(payments);
    }

    public PaymentsModel findById(int id) {
        return payments.stream()
                .filter(p -> p.getIdPayment() == id)
                .findFirst()
                .orElse(null);
    }

    public PaymentsModel addPayment(PaymentsModel payment) {
        payment.setIdPayment(idCounter.getAndIncrement());
        payments.add(payment);
        return payment;
    }

    public PaymentsModel updatePayment(PaymentsModel payment) {
        for (int i = 0; i < payments.size(); i++) {
            if (payments.get(i).getIdPayment() == payment.getIdPayment()) {
                payments.set(i, payment);
                return payment;
            }
        }
        return null;
    }

    public void deletePayment(int id) {
        payments.removeIf(p -> p.getIdPayment() == id);
    }
}
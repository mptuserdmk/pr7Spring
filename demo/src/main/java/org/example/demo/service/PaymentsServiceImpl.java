package org.example.demo.service;

import org.example.demo.model.PaymentsModel;
import org.example.demo.repository.PaymentsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentsServiceImpl implements PaymentsService {

    private final PaymentsRepository paymentsRepository;

    public PaymentsServiceImpl(PaymentsRepository paymentsRepository) {
        this.paymentsRepository = paymentsRepository;
    }

    @Override
    public List<PaymentsModel> findAll() {
        return paymentsRepository.findAll();
    }

    @Override
    public PaymentsModel findById(int id) {
        return paymentsRepository.findById(id);
    }

    @Override
    public PaymentsModel addPayment(PaymentsModel payment) {
        return paymentsRepository.addPayment(payment);
    }

    @Override
    public PaymentsModel updatePayment(PaymentsModel payment) {
        return paymentsRepository.updatePayment(payment);
    }

    @Override
    public void deletePayment(int id) {
        paymentsRepository.deletePayment(id);
    }
}
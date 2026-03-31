package org.example.demo.controller;

import org.example.demo.model.PaymentsModel;
import org.example.demo.service.PaymentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/payments")
public class PaymentsController {

    @Autowired
    private PaymentsService paymentsService;

    @GetMapping
    public String allPayments(Model model) {
        List<PaymentsModel> payments = paymentsService.findAll();
        model.addAttribute("payments", payments);
        return "payments";
    }

    @PostMapping("/add")
    public String addPayment(@RequestParam int requestId,
                             @RequestParam String paymentDate,
                             @RequestParam double paymentAmount,
                             @RequestParam String paymentMethod) {

        PaymentsModel payment = new PaymentsModel(
                0, requestId, paymentDate, paymentAmount, paymentMethod
        );

        paymentsService.addPayment(payment);
        return "redirect:/payments";
    }

    @PostMapping("/update")
    public String updatePayment(@RequestParam int id,
                                @RequestParam int requestId,
                                @RequestParam String paymentDate,
                                @RequestParam double paymentAmount,
                                @RequestParam String paymentMethod) {

        PaymentsModel payment = new PaymentsModel(
                id, requestId, paymentDate, paymentAmount, paymentMethod
        );

        paymentsService.updatePayment(payment);
        return "redirect:/payments";
    }

    @PostMapping("/delete")
    public String deletePayment(@RequestParam int id) {
        paymentsService.deletePayment(id);
        return "redirect:/payments";
    }
}
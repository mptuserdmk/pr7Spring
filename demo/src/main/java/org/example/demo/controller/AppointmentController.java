package org.example.demo.controller;

import jakarta.validation.Valid;
import org.example.demo.model.Appointment;
import org.example.demo.service.AppointmentService;
import org.example.demo.service.EmailMockService;
import org.example.demo.service.PaymentAndReceiptService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final PaymentAndReceiptService paymentService;
    private final EmailMockService emailService;
    private final org.example.demo.service.UserService userService;
    private final org.example.demo.service.ServiceService serviceService;

    public AppointmentController(AppointmentService appointmentService, 
                                PaymentAndReceiptService paymentService, 
                                EmailMockService emailService, org.example.demo.service.UserService userService, org.example.demo.service.ServiceService serviceService) {
        this.appointmentService = appointmentService;
        this.paymentService = paymentService;
        this.emailService = emailService;
        this.userService = userService;
        this.serviceService = serviceService;
    }
    
    @GetMapping
    public String listAppointments(
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            Model model) {
        
        Page<Appointment> appointmentPage;
        if (search != null && !search.isEmpty()) {
            appointmentPage = appointmentService.searchByStatus(search, PageRequest.of(page, size));
            model.addAttribute("search", search);
        } else {
            appointmentPage = appointmentService.findAll(PageRequest.of(page, size));
        }
        
        model.addAttribute("appointments", appointmentPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", appointmentPage.getTotalPages());
        model.addAttribute("totalItems", appointmentPage.getTotalElements());
        
        return "appointments";
    }

    @GetMapping("/book")
    public String bookForm(Model model) {
        model.addAttribute("appointment", new Appointment());
        model.addAttribute("users", userService.findAll());
        model.addAttribute("services", serviceService.findAll());
        return "appointment-form";
    }

    @PostMapping("/book")
    public String bookAppointment(@Valid @ModelAttribute("appointment") Appointment appointment, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("users", userService.findAll());
            model.addAttribute("services", serviceService.findAll());
            return "appointment-form";
        }
        
        if (appointment.getStatus() == null || appointment.getStatus().isEmpty()) {
            appointment.setStatus("PENDING");
        }
        
        Appointment saved = appointmentService.save(appointment);
        
        paymentService.processPayment(saved.getId(), "1000");
        paymentService.generateReceipt(saved.getId(), "Booking created", "1000");
        emailService.sendConfirmationEmail("client@example.com", "Barbershop Booking");

        return "redirect:/appointments";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Appointment appointment = appointmentService.findById(id);
        if (appointment == null) {
            return "redirect:/appointments";
        }
        model.addAttribute("appointment", appointment);
        model.addAttribute("users", userService.findAll());
        model.addAttribute("services", serviceService.findAll());
        return "appointment-form";
    }

    @PostMapping("/edit/{id}")
    public String updateAppointment(@PathVariable Long id, @Valid @ModelAttribute("appointment") Appointment appointment, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("users", userService.findAll());
            model.addAttribute("services", serviceService.findAll());
            return "appointment-form";
        }
        appointment.setId(id);
        appointmentService.save(appointment);
        return "redirect:/appointments";
    }

    @GetMapping("/delete/{id}")
    public String deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteById(id);
        return "redirect:/appointments";
    }
}

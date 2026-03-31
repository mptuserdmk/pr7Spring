package org.example.demo.controller;

import org.example.demo.model.RepairServicesModel;
import org.example.demo.service.RepairServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/repair_services")
public class RepairServicesController {

    @Autowired
    private RepairServicesService service;

    @GetMapping
    public String allServices(Model model) {
        List<RepairServicesModel> services = service.findAll();
        model.addAttribute("services", services);
        return "repair_services";
    }

    @PostMapping("/add")
    public String addService(@RequestParam String name,
                             @RequestParam double price) {

        RepairServicesModel serviceModel =
                new RepairServicesModel(0, name, price);

        service.addService(serviceModel);
        return "redirect:/repair_services";
    }

    @PostMapping("/update")
    public String updateService(@RequestParam int id,
                                @RequestParam String name,
                                @RequestParam double price) {

        RepairServicesModel serviceModel =
                new RepairServicesModel(id, name, price);

        service.updateService(serviceModel);
        return "redirect:/repair_services";
    }

    @PostMapping("/delete")
    public String deleteService(@RequestParam int id) {
        service.deleteService(id);
        return "redirect:/repair_services";
    }
}
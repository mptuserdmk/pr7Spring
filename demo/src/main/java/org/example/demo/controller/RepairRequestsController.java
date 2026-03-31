package org.example.demo.controller;

import org.example.demo.model.RepairRequestsModel;
import org.example.demo.service.RepairRequestsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/repair_requests")
public class RepairRequestsController {

    @Autowired
    private RepairRequestsService repairRequestsService;

    @GetMapping
    public String allRequests(Model model) {
        List<RepairRequestsModel> requests = repairRequestsService.findAll();
        model.addAttribute("requests", requests);
        return "repair_requests";
    }

    @PostMapping("/add")
    public String addRequest(@RequestParam String description,
                             @RequestParam String status,
                             @RequestParam String createdAt) {

        RepairRequestsModel request =
                new RepairRequestsModel(0, description, status, createdAt);

        repairRequestsService.addRequest(request);
        return "redirect:/repair_requests";
    }

    @PostMapping("/update")
    public String updateRequest(@RequestParam int id,
                                @RequestParam String description,
                                @RequestParam String status,
                                @RequestParam String createdAt) {

        RepairRequestsModel request =
                new RepairRequestsModel(id, description, status, createdAt);

        repairRequestsService.updateRequest(request);
        return "redirect:/repair_requests";
    }

    @PostMapping("/delete")
    public String deleteRequest(@RequestParam int id) {
        repairRequestsService.deleteRequest(id);
        return "redirect:/repair_requests";
    }
}
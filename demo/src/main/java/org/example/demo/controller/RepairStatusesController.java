package org.example.demo.controller;

import org.springframework.ui.Model;
import org.example.demo.model.RepairStatusesModel;
import org.example.demo.service.RepairStatusesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/repair_statuses")
public class RepairStatusesController {

    @Autowired
    private RepairStatusesService service;

    @GetMapping
    public String page(Model model) {
        model.addAttribute("statuses", service.findAll());
        return "repair_statuses";
    }

    @PostMapping("/add")
    public String add(@RequestParam String name) {
        service.add(new RepairStatusesModel(0, name));
        return "redirect:/repair_statuses";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam int id) {
        service.delete(id);
        return "redirect:/repair_statuses";
    }
}

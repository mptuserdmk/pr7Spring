package org.example.demo.controller;

import org.example.demo.model.RolesModel;
import org.example.demo.service.RolesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/roles")
public class RolesController {

    private final RolesService service;

    public RolesController(RolesService service) {
        this.service = service;
    }

    @GetMapping
    public String page(Model model) {
        model.addAttribute("roles", service.findAll());
        return "roles";
    }

    @PostMapping("/add")
    public String add(@RequestParam String roleName) {
        service.addRole(roleName);
        return "redirect:/roles";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam int id) {
        service.deleteRole(id);
        return "redirect:/roles";
    }
}
package org.example.demo.controller;

import org.example.demo.service.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UsersService service;

    public UsersController(UsersService service) {
        this.service = service;
    }

    @GetMapping
    public String page(Model model) {
        model.addAttribute("users", service.findAll());
        return "users";
    }

    @PostMapping("/add")
    public String add(@RequestParam String username,
                      @RequestParam String email,
                      @RequestParam int roleId) {
        service.addUser(username, email, roleId);
        return "redirect:/users";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam int id) {
        service.deleteUser(id);
        return "redirect:/users";
    }
}
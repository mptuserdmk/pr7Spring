package org.example.demo.controller;

import org.example.demo.model.Role;
import org.example.demo.model.User;
import org.example.demo.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String adminUsers(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        model.addAttribute("roles", Role.values());
        return "admin/users";
    }

    @PostMapping("/users/{id}/role")
    public String changeUserRole(@PathVariable Long id,
                                 @RequestParam String role,
                                 @AuthenticationPrincipal User currentUser,
                                 RedirectAttributes redirectAttributes) {
        try {
            Role newRole = Role.valueOf(role);
            userService.changeRole(id, newRole, currentUser.getEmail());
            redirectAttributes.addFlashAttribute("success", "Роль пользователя успешно обновлена");
        } catch (IllegalStateException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", "Некорректная роль или пользователь не найден");
        }
        return "redirect:/admin/users";
    }
}

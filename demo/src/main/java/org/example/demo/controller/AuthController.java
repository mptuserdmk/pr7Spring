package org.example.demo.controller;

import org.example.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout,
                            Model model) {
        if (error != null) {
            model.addAttribute("errorMsg", "Неверный email или пароль");
        }
        if (logout != null) {
            model.addAttribute("logoutMsg", "Вы успешно вышли из системы");
        }
        return "login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String email,
                               @RequestParam String password,
                               @RequestParam String confirmPassword,
                               RedirectAttributes redirectAttributes) {
        // Валидация
        if (email == null || email.isBlank()) {
            redirectAttributes.addFlashAttribute("error", "Email не может быть пустым");
            return "redirect:/register";
        }
        if (password == null || password.length() < 8) {
            redirectAttributes.addFlashAttribute("error", "Пароль должен быть не менее 8 символов");
            return "redirect:/register";
        }
        if (!password.equals(confirmPassword)) {
            redirectAttributes.addFlashAttribute("error", "Пароли не совпадают");
            return "redirect:/register";
        }

        try {
            userService.registerUser(email.trim(), password);
            redirectAttributes.addFlashAttribute("success", "Аккаунт создан! Войдите в систему.");
            return "redirect:/login";
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/register";
        }
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "access-denied";
    }
}

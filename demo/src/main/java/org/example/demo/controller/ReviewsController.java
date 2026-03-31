package org.example.demo.controller;

import org.example.demo.service.ReviewsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/reviews")
public class ReviewsController {

    private final ReviewsService service;

    public ReviewsController(ReviewsService service) {
        this.service = service;
    }

    @GetMapping
    public String page(Model model) {
        model.addAttribute("reviews", service.findAll());
        return "reviews";
    }

    @PostMapping("/add")
    public String add(@RequestParam int userId,
                      @RequestParam String comment,
                      @RequestParam int rating) {
        service.addReview(userId, comment, rating);
        return "redirect:/reviews";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam int id) {
        service.deleteReview(id);
        return "redirect:/reviews";
    }
}

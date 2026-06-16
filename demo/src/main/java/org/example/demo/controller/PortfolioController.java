package org.example.demo.controller;

import jakarta.validation.Valid;
import org.example.demo.model.Portfolio;
import org.example.demo.service.PortfolioService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/portfolios")
public class PortfolioController {

    private final PortfolioService portfolioService;
    private final org.example.demo.service.UserService userService;

    public PortfolioController(PortfolioService portfolioService, org.example.demo.service.UserService userService) {
        this.portfolioService = portfolioService;
        this.userService = userService;
    }

    @GetMapping
    public String listPortfolios(
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            Model model) {
        
        Page<Portfolio> portfolioPage;
        if (search != null && !search.isEmpty()) {
            portfolioPage = portfolioService.searchByDescription(search, PageRequest.of(page, size));
            model.addAttribute("search", search);
        } else {
            portfolioPage = portfolioService.findAll(PageRequest.of(page, size));
        }
        
        model.addAttribute("portfolios", portfolioPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", portfolioPage.getTotalPages());
        model.addAttribute("totalItems", portfolioPage.getTotalElements());
        
        return "portfolio";
    }

    @GetMapping("/add")
    public String addPortfolioForm(Model model) {
        model.addAttribute("portfolio", new Portfolio());
        model.addAttribute("users", userService.findAll());
        return "portfolio-form";
    }

    @PostMapping("/add")
    public String addPortfolio(@Valid @ModelAttribute("portfolio") Portfolio portfolio, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("users", userService.findAll());
            return "portfolio-form";
        }
        portfolioService.save(portfolio);
        return "redirect:/portfolios";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Portfolio portfolio = portfolioService.findById(id);
        if (portfolio == null) {
            return "redirect:/portfolios";
        }
        model.addAttribute("portfolio", portfolio);
        model.addAttribute("users", userService.findAll());
        return "portfolio-form";
    }

    @PostMapping("/edit/{id}")
    public String updatePortfolio(@PathVariable Long id, @Valid @ModelAttribute("portfolio") Portfolio portfolio, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("users", userService.findAll());
            return "portfolio-form";
        }
        portfolio.setId(id);
        portfolioService.save(portfolio);
        return "redirect:/portfolios";
    }

    @GetMapping("/delete/{id}")
    public String deletePortfolio(@PathVariable Long id) {
        portfolioService.deleteById(id);
        return "redirect:/portfolios";
    }
}

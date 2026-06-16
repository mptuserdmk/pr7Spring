package org.example.demo.controller;

import jakarta.validation.Valid;
import org.example.demo.model.Category;
import org.example.demo.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String listCategories(
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            Model model) {
        
        Page<Category> categoryPage;
        if (search != null && !search.isEmpty()) {
            categoryPage = categoryService.searchByName(search, PageRequest.of(page, size));
            model.addAttribute("search", search);
        } else {
            categoryPage = categoryService.findAll(PageRequest.of(page, size));
        }
        
        model.addAttribute("categories", categoryPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", categoryPage.getTotalPages());
        model.addAttribute("totalItems", categoryPage.getTotalElements());
        
        return "categories";
    }

    @GetMapping("/add")
    public String addCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "category-form";
    }

    @PostMapping("/add")
    public String addCategory(@Valid @ModelAttribute("category") Category category, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "category-form";
        }
        categoryService.save(category);
        return "redirect:/categories";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Category category = categoryService.findById(id);
        if (category == null) {
            return "redirect:/categories";
        }
        model.addAttribute("category", category);
        return "category-form";
    }

    @PostMapping("/edit/{id}")
    public String updateCategory(@PathVariable Long id, @Valid @ModelAttribute("category") Category category, BindingResult result) {
        if (result.hasErrors()) {
            return "category-form";
        }
        category.setId(id);
        categoryService.save(category);
        return "redirect:/categories";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.deleteById(id);
        return "redirect:/categories";
    }
}

package org.example.demo.controller;

import jakarta.validation.Valid;
import org.example.demo.model.Service;
import org.example.demo.service.ServiceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/services")
public class ServiceController {

    private final ServiceService serviceService;
    private final org.example.demo.service.CategoryService categoryService;

    public ServiceController(ServiceService serviceService, org.example.demo.service.CategoryService categoryService) {
        this.serviceService = serviceService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String listServices(
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            Model model) {
        
        Page<Service> servicePage;
        if (search != null && !search.isEmpty()) {
            servicePage = serviceService.searchByName(search, PageRequest.of(page, size));
            model.addAttribute("search", search);
        } else {
            servicePage = serviceService.findAll(PageRequest.of(page, size));
        }
        
        model.addAttribute("services", servicePage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", servicePage.getTotalPages());
        model.addAttribute("totalItems", servicePage.getTotalElements());
        
        return "services";
    }

    @GetMapping("/add")
    public String addServiceForm(Model model) {
        model.addAttribute("service", new Service());
        model.addAttribute("categories", categoryService.findAll());
        return "add-service";
    }

    @PostMapping("/add")
    public String addService(@Valid @ModelAttribute("service") Service service, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categories", categoryService.findAll());
            return "add-service";
        }
        serviceService.save(service);
        return "redirect:/services";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Service service = serviceService.findById(id);
        if (service == null) {
            return "redirect:/services";
        }
        model.addAttribute("service", service);
        model.addAttribute("categories", categoryService.findAll());
        return "add-service";
    }

    @PostMapping("/edit/{id}")
    public String updateService(@PathVariable Long id, @Valid @ModelAttribute("service") Service service, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categories", categoryService.findAll());
            return "add-service";
        }
        service.setId(id);
        serviceService.save(service);
        return "redirect:/services";
    }

    @GetMapping("/delete/{id}")
    public String deleteService(@PathVariable Long id) {
        serviceService.deleteById(id);
        return "redirect:/services";
    }
}

package org.example.demo.controller;

import org.example.demo.model.RepairRequestsItemsModel;
import org.example.demo.service.RepairRequestsItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/repair_request_items")
public class RepairRequestsItemsController {

    @Autowired
    private RepairRequestsItemsService service;

    @GetMapping
    public String allItems(Model model) {
        List<RepairRequestsItemsModel> items = service.findAll();
        model.addAttribute("items", items);
        return "repair_request_items";
    }

    @PostMapping("/add")
    public String addItem(@RequestParam int requestId,
                          @RequestParam String itemName,
                          @RequestParam int quantity,
                          @RequestParam double price) {

        RepairRequestsItemsModel item =
                new RepairRequestsItemsModel(0, requestId, itemName, quantity, price);

        service.addItem(item);
        return "redirect:/repair_request_items";
    }

    @PostMapping("/update")
    public String updateItem(@RequestParam int id,
                             @RequestParam int requestId,
                             @RequestParam String itemName,
                             @RequestParam int quantity,
                             @RequestParam double price) {

        RepairRequestsItemsModel item =
                new RepairRequestsItemsModel(id, requestId, itemName, quantity, price);

        service.updateItem(item);
        return "redirect:/repair_request_items";
    }

    @PostMapping("/delete")
    public String deleteItem(@RequestParam int id) {
        service.deleteItem(id);
        return "redirect:/repair_request_items";
    }
}
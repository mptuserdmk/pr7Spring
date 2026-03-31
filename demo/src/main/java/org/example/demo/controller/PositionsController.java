package org.example.demo.controller;

import org.example.demo.model.PositionsModel;
import org.example.demo.service.PositionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/positions")
public class PositionsController {

    @Autowired
    private PositionsService positionsService;

    @GetMapping
    public String allPositions(Model model) {
        List<PositionsModel> positions = positionsService.findAll();
        model.addAttribute("positions", positions);
        return "positions";
    }

    @PostMapping("/add")
    public String addPosition(@RequestParam String name,
                              @RequestParam double baseSalary) {

        PositionsModel position = new PositionsModel(0, name, baseSalary);
        positionsService.addPosition(position);

        return "redirect:/positions";
    }

    @PostMapping("/update")
    public String updatePosition(@RequestParam int id,
                                 @RequestParam String name,
                                 @RequestParam double baseSalary) {

        PositionsModel position = new PositionsModel(id, name, baseSalary);
        positionsService.updatePosition(position);

        return "redirect:/positions";
    }

    @PostMapping("/delete")
    public String deletePosition(@RequestParam int id) {
        positionsService.deletePosition(id);
        return "redirect:/positions";
    }
}
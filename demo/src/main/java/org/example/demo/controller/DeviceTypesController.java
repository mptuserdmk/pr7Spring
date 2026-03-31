package org.example.demo.controller;

import org.example.demo.model.DeviceTypesModel;
import org.example.demo.service.DeviceTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/device_types")
public class DeviceTypesController {

    @Autowired
    private DeviceTypesService service;

    @GetMapping
    public String page(Model model) {
        model.addAttribute("deviceTypes", service.findAll());
        return "device_types";
    }

    @PostMapping("/add")
    public String add(@RequestParam String deviceTypeName) {
        service.addDeviceType(new DeviceTypesModel(0, deviceTypeName));
        return "redirect:/device_types";
    }

    @PostMapping("/update")
    public String update(@RequestParam int id, @RequestParam String deviceTypeName) {
        service.updateDeviceType(new DeviceTypesModel(id, deviceTypeName));
        return "redirect:/device_types";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam int id) {
        service.deleteDeviceType(id);
        return "redirect:/device_types";
    }
}
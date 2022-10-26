package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.service.AccidentService;
import java.util.Set;

@Controller
@AllArgsConstructor
public class AccidentControl {
    private final AccidentService service;

    @GetMapping("/createAccident")
    public String viewCreateAccident(Model model) {
        model.addAttribute("types", service.getTypes());
        model.addAttribute("rules", service.getRules());
        return "createAccident";
    }

    @PostMapping("/saveAccident")
    public String save(@ModelAttribute Accident accident, @RequestParam(value = "rIds", required = false) Set<Integer> ruleIds) {
        accident.setType(service.getTypeById(accident.getType().getId()));
        if (ruleIds != null) {
            ruleIds.forEach(x -> accident.getRules().add(service.getRuleById(x)));
        }
        System.out.println(accident);
        service.create(accident);
        return "redirect:/index";
    }

    @GetMapping("/editAccident")
    public String getUpdate(Model model, @RequestParam("id") int id) {
        model.addAttribute("accident", service.getById(id));
        model.addAttribute("types", service.getTypes());
        model.addAttribute("rules", service.getRules());
        return "updateAccident";
    }

    @PostMapping("/updateAccident")
    public String update(@ModelAttribute Accident accident, @RequestParam(value = "rIds", required = false) Set<Integer> ruleIds) {
        accident.setType(service.getTypeById(accident.getType().getId()));
        if (ruleIds != null) {
            ruleIds.forEach(x -> accident.getRules().add(service.getRuleById(x)));
        }
        service.update(accident);
        return "redirect:/index";
    }

}
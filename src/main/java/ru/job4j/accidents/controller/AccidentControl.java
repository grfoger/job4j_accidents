package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.service.AccidentService;

@Controller
@AllArgsConstructor
public class AccidentControl {
    private final AccidentService accidents;

    @GetMapping("/createAccident")
    public String viewCreateAccident(Model model) {
        model.addAttribute("types", accidents.getTypes());
        return "createAccident";
    }

    @PostMapping("/saveAccident")
    public String save(@ModelAttribute Accident accident) {
        accident.setType(accidents.getTypeById(accident.getType().getId()));
        accidents.create(accident);
        return "redirect:/index";
    }

    @GetMapping("/editAccident")
    public String getUpdate(Model model, @RequestParam("id") int id) {
        model.addAttribute("accident", accidents.getById(id));
        model.addAttribute("types", accidents.getTypes());
        return "updateAccident";
    }

    @PostMapping("/updateAccident")
    public String update(@ModelAttribute Accident accident) {
        accident.setType(accidents.getTypeById(accident.getType().getId()));
        accidents.update(accident);
        return "redirect:/index";
    }

}
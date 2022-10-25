package ru.job4j.accidents.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.service.AccidentService;

import java.util.List;


@Controller
public class IndexController {

    private final AccidentService service;

    public IndexController(AccidentService service) {
        this.service = service;
    }


    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("user", "Arhip Myravsky");
        model.addAttribute("accidents", service.getAll());
        return "index";
    }

    @GetMapping("/")
    public String indexBlank(Model model) {
        model.addAttribute("user", "Arhip Myravsky");
        model.addAttribute("accidents", service.getAll());
        System.out.println(service.getAll());
        return "index";
    }

    @GetMapping("/accidents")
    public String accidents(Model model) {
        model.addAttribute("user", "Arhip Myravsky");
        model.addAttribute("accidents", service.getAll());
        System.out.println(service.getAll());
        return "index";
    }
}

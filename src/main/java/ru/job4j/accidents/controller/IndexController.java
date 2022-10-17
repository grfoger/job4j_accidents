package ru.job4j.accidents.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accidents.model.Accident;

import java.util.List;


@Controller
public class IndexController {
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("user", "Arhip Myravsky");
        model.addAttribute("accidents", List.of(new Accident(1, "Парковка", "Остановка в неполженном месте", "ул.Герцина")));
        return "index";
    }
}

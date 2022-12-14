package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accidents.model.User;
import ru.job4j.accidents.repositorydata.AuthorityRepository;
import ru.job4j.accidents.repositorydata.UserRepository;

@AllArgsConstructor
@Controller
public class RegControl {
    private final PasswordEncoder encoder;
    private final UserRepository users;
    private final AuthorityRepository authorities;

    @PostMapping("/reg")
    public String regSave(@ModelAttribute User user) {
        user.setEnabled(true);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setAuthority(authorities.findByAuthority("ROLE_USER"));
        try {
            users.save(user);
        } catch (Exception e) {
            return "redirect:/reg?error=true";
        }
        return "redirect:/login";
    }

    @GetMapping("/reg")
    public String regPage(@RequestParam(value = "error", required = false) String error,
                          Model model) {
        if (error != null) {
            model.addAttribute("errorMessage",
                    "Ошибка регистрации. Возможно такой пользователь уже существует.");
        }
        return "reg";
    }
}

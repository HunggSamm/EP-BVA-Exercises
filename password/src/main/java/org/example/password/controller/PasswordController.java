package org.example.password.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PasswordController {

    @PostMapping("/validatePassword")
    public String validatePassword(@RequestParam("password") String password, Model model) {
        if (password.length() >= 6 && password.length() <= 10) {
            model.addAttribute("message", "Password is valid.");
        } else {
            model.addAttribute("message", "Password must be between 6 and 10 characters.");
        }
        return "password";
    }
    @GetMapping("/validatePassword")
    public String validatePassword() {
        return "password";
    }
}
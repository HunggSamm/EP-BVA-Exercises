package org.example.pizza.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PizzaOrderController {

    @PostMapping("/order")
    public String orderPizza(@RequestParam("quantity") int quantity, Model model) {
        if (quantity >= 1 && quantity <= 10) {
            model.addAttribute("message", "Order successful!");
        } else {
            model.addAttribute("message", "Only 10 Pizza can be ordered");
        }
        return "order";
    }
    @GetMapping("/order")
    public String showOrderForm(Model model) {
        return "order";  // Hiển thị trang order.html
    }
}
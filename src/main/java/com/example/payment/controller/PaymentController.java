package com.example.payment.controller;

import com.example.payment.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PaymentController {

    private final List<User> users = new ArrayList<>();

    private int nextUserId = 1;


    // Home page
    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute("users", users);

        return "index";
    }


    // Add new user
    @PostMapping("/add-user")
    public String addUser(@RequestParam String name) {

        User user = new User(
                nextUserId++,
                name,
                0.0
        );

        users.add(user);

        return "redirect:/";
    }


    // Credit amount to wallet
    @PostMapping("/credit")
    public String creditMoney(
            @RequestParam int userId,
            @RequestParam double amount) {

        for (User user : users) {

            if (user.getId() == userId) {

                user.setBalance(
                        user.getBalance() + amount
                );

                break;
            }
        }

        return "redirect:/";
    }
}

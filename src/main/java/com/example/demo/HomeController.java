package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class HomeController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    MessageRepository messageRepository;

    @RequestMapping("/")
    public String displayFeed(Model model){
        model.addAttribute("messages", messageRepository.findAll());
        return "home";
    }

    @RequestMapping("/login")
    public String login(){return "login";}

    @PostMapping("/logout")
    public String logout(){return "redirect:/login?logout=true";}

    @RequestMapping("/admin")
    public String adminPage(Model model){
        model.addAttribute("users", userRepository.findAll());
        return "admin";
    }

    @GetMapping("/newUser")
    public String register(Model model){
        model.addAttribute("user", new User());
        return "newUser";
    }

    @RequestMapping("/profile/{id}")
    public String profile(Model model, @PathVariable("id") long id){
        model.addAttribute("user", userRepository.findById(id).get());
        return "profile";
    }


}

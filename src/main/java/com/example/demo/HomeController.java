package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
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
        public String displayFeed(Model model, Principal principal){
            if(principal != null) {
                String username = principal.getName();
                model.addAttribute("user", userRepository.findByUsername(username));
            }
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
        model.addAttribute("messages", messageRepository.findAll());
        return "admin";
    }

    @GetMapping("/newUser")
    public String registerUser(Model model){
        model.addAttribute("user", new User());
        return "newUser";
    }
    @PostMapping("/newUser")
    public String processUser(@Valid User user, BindingResult result){
        if(result.hasErrors()){
            return "newUser";
        }
        else{
            Role userRole = new Role(user.getUsername(), "ROLE_USER");
            userRepository.save(user);
            roleRepository.save(userRole);
            return "login";
        }
    }

    @GetMapping("/newMessage")
    public String postMessage(Model model, Principal principal){
        Message message = new Message();
        String username = principal.getName();
        message.setUser(userRepository.findByUsername(username));
        model.addAttribute("message", message);
        return "newMessage";
    }

    @PostMapping("/newMessage")
    public String processMessage(@Valid Message message, BindingResult result){
        if(result.hasErrors()){
            return "newMessage";
        }
        else{
            messageRepository.save(message);
            return "redirect:/";
        }
    }

    @RequestMapping("/profile")
    public String profile(Model model, Principal principal){
            String username = principal.getName();
        model.addAttribute("user", userRepository.findByUsername(username));
        return "profile";
    }

    @RequestMapping("/disable/{id}")
    public String disableUser(@PathVariable("id") long id, Model model){
            if(userRepository.findById(id).get().isEnabled()) {
                userRepository.findById(id).get().setEnabled(false);
            }
            else if(!userRepository.findById(id).get().isEnabled()){
                userRepository.findById(id).get().setEnabled(true);
            }

        model.addAttribute("users", userRepository.findAll());
        return "admin";
    }

    @RequestMapping("/remove/{id}")
    public String removePost(@PathVariable("id") long id){
            messageRepository.deleteById(id);

            return "redirect:/admin";
    }

    @RequestMapping("/posts")
    public String displayUserPosts(Principal principal, Model model){
            String username = principal.getName();
            model.addAttribute("user", userRepository.findByUsername(username));
            return "posts";

    }

}

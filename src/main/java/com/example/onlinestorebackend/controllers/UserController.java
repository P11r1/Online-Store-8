package com.example.onlinestorebackend.controllers;

import com.example.onlinestorebackend.exceptions.UserNotFoundException;
import com.example.onlinestorebackend.models.User;
import com.example.onlinestorebackend.services.AuthorService;
import com.example.onlinestorebackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author Bahadir Tasli
 * @Date 3/22/2023
 */


@RestController
@Controller
@RequestMapping("/user")

public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthorService authorService;

    @GetMapping
    public String showAllUserPage(Model model) {
        model.addAttribute("users",userService.findAllUsers());
        return "user/list-user";
    }

    @GetMapping("/signup")
    public String showSignupPage(Model model, @ModelAttribute("user")User user,@ModelAttribute("message")String message,
                                 @ModelAttribute("messageType") String messageType) {
        model.addAttribute("authorities", authorService.findAllAuthorities());
        return "user/create-user";
    }
    
    @PostMapping("/signup")
    public String createUser(User user, RedirectAttributes redirectAttributes) {
        try {
            userService.findUserByFullName(user.getFullName());
            redirectAttributes.addFlashAttribute("message",String.format("User(%s) already exist", user.getFullName()));
            redirectAttributes.addFlashAttribute("messageType","error");
            return "redirect:/user/signup";
        } catch (UserNotFoundException e) {
            userService.createUser(user);
            redirectAttributes.addFlashAttribute("message", "Signup successful!");
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/";
        }

    }
}

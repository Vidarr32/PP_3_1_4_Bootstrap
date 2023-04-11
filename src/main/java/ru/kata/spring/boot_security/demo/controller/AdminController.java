package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.servise.RolesService;
import ru.kata.spring.boot_security.demo.servise.UsersService;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UsersService userService;
    private final RolesService rolesService;

    @Autowired
    public AdminController(UsersService userService, RolesService rolesService) {
        this.userService = userService;
        this.rolesService = rolesService;
    }

    @GetMapping
    public String show(Principal principal, ModelMap model) {
        model.addAttribute("activeUser", userService.findByUsername(principal.getName()));
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("allroles", rolesService.getAllRoles());
        model.addAttribute("newuser", new User());
        return "admin";
    }

    @PostMapping
    public String createUser(@ModelAttribute("newuser") User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("edituser") User user, @PathVariable("id") long id) {
        System.out.println(user.toString());
        User user1 = userService.getUserById(id);
        System.out.println(user1);
        userService.updateUser(id, user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}

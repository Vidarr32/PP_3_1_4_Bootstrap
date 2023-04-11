package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.servise.RolesService;
import ru.kata.spring.boot_security.demo.servise.UsersService;

import java.security.Principal;


@Controller
@RequestMapping
public class UsersController {

    private final UsersService userService;
    private final RolesService rolesService;

    @Autowired
    public UsersController(UsersService userService, RolesService rolesService) {
        this.userService = userService;
        this.rolesService = rolesService;
    }

    @GetMapping({"/user"})
    public String showUserInfo(Principal principal, ModelMap model) {
        model.addAttribute("activeUser", userService.findByUsername(principal.getName()));
        model.addAttribute("allroles", rolesService.getAllRoles());
        return "user";
    }
}

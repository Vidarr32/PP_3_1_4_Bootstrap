package ru.kata.spring.boot_security.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.servise.RolesService;
import ru.kata.spring.boot_security.demo.servise.UsersService;

import java.util.ArrayList;
import java.util.Arrays;

@Component
public class InitDataBase implements CommandLineRunner {

    private final UsersService usersService;

    private final RolesService rolesService;

    @Autowired
    public InitDataBase(UsersService usersService, RolesService rolesService) {
        this.usersService = usersService;
        this.rolesService = rolesService;
    }

    @Override
    public void run(String... args) throws Exception {
        Role roleUser = new Role("ROLE_USER");
        Role roleAdmin = new Role("ROLE_ADMIN");

        if (rolesService.getAllRoles().isEmpty()) {
            rolesService.saveRole(roleUser);
            rolesService.saveRole(roleAdmin);
        }

        User user = new User("Ivan", "Ivanov", 18, "user@gmail.ru", "user",
                new ArrayList<>(Arrays.asList(roleUser)));
        User admin = new User("Alex", "Volkov",22, "admin@gmail.ru", "admin",
                new ArrayList<>(Arrays.asList(roleAdmin, roleUser)));

        usersService.saveUser(user);
        usersService.saveUser(admin);
    }
}

package ua.com.nc.nctrainingproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.com.nc.nctrainingproject.services.AuthorizationService;

@RequestMapping("/login")
@RestController
public class AuthorizationController {

    @Autowired
    AuthorizationService authorizationService;

    @PostMapping
    public boolean login(@RequestParam String login, @RequestParam String password) {
        return authorizationService.auth(login, password);
    }
}

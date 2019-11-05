package ua.com.nc.nctrainingproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.UserPostgreDAO;
import ua.com.nc.nctrainingproject.services.RegistrationService;

@RequestMapping("/registration")
@RestController
@CrossOrigin
public class RegistrationController {

    @Autowired
    UserPostgreDAO userPostgreDAO;

    @Autowired
    RegistrationService registrationService;

    @PostMapping
    public boolean registration(@RequestParam(name = "login") String login,
                                @RequestParam(name = "password") String password,
                                @RequestParam(name = "email") String email) {
        return registrationService.reg(login, password, email);
    }
}

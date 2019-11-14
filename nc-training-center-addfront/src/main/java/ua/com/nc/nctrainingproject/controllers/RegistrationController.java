package ua.com.nc.nctrainingproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.UserPostgreDAO;
import ua.com.nc.nctrainingproject.services.RegistrationService;

@RequestMapping("/registration")
@RestController
public class RegistrationController {

    @Autowired
    UserPostgreDAO userPostgreDAO;

    @Autowired
    RegistrationService registrationService;

    @PostMapping
    public boolean registration(@RequestParam String login,
                                @RequestParam String password,
                                @RequestParam String email) {
        return registrationService.reg(login, password, email);
    }
}

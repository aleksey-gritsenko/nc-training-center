package ua.com.nc.nctrainingproject.controllers;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.com.nc.nctrainingproject.models.User;
import ua.com.nc.nctrainingproject.services.AuthorizationService;

import java.util.Map;

@RestController
@CrossOrigin
public class AuthorizationController {
    private final AuthorizationService authorizationService;

    @Autowired
    public AuthorizationController(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map<String, Object> login(@RequestParam(name = "login") String login,
                                     @RequestParam(name = "password") String password) {

        return authorizationService.auth(login, password);
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public User registration(@RequestParam(name = "login") String login,
                             @RequestParam(name = "password") String password,
                             @RequestParam(name = "email") String email) {

        return authorizationService.register(login, password, email);
    }
}
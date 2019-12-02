package ua.com.nc.nctrainingproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.nc.nctrainingproject.models.User;
import ua.com.nc.nctrainingproject.services.AuthorizationService;

import java.util.Optional;

@RestController
@CrossOrigin
public class AuthorizationController {
    private final AuthorizationService authorizationService;


    @Autowired
    public AuthorizationController(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestParam(name = "login") String login,
                                   @RequestParam(name = "password") String password) {
        User response = authorizationService.auth(login, password);
        return Optional.ofNullable(response).map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ResponseEntity<?> registration(@RequestParam(name = "login") String login,
                                          @RequestParam(name = "password") String password,
                                          @RequestParam(name = "email") String email) {
        User response = authorizationService.register(login, password, email);
        return Optional.ofNullable(response).map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }
}

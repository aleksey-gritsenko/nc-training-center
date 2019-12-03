package ua.com.nc.nctrainingproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
//import ua.com.nc.nctrainingproject.configuration.security.AuthentificateService;
//import ua.com.nc.nctrainingproject.configuration.security.AuthentificateService;
//import ua.com.nc.nctrainingproject.configuration.security.CustomAuthentificationProvider;
//import ua.com.nc.nctrainingproject.configuration.security.MyUserDetailsServise;
import ua.com.nc.nctrainingproject.models.User;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.UserBooksPostgreDAO;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.UserPostgreDAO;
import ua.com.nc.nctrainingproject.services.AuthorizationService;

import javax.servlet.http.HttpSession;

@EnableWebSecurity
@RestController
@CrossOrigin
public class AuthorizationController {
    private final AuthorizationService authorizationService;


    @Autowired
    public AuthorizationController(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public User login(@RequestParam(name = "login") String login,
                      @RequestParam(name = "password") String password) {

        return authorizationService.auth(login, password);
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public User registration(@RequestParam(name = "login") String login,
                             @RequestParam(name = "password") String password,
                             @RequestParam(name = "email") String email) {

        return authorizationService.register(login, password, email);
    }

    @RequestMapping(value = "/testFeature", method = RequestMethod.POST)
    public void loginUser(@RequestParam(name = "login") String login,
                          @RequestParam(name = "password") String password) {
        authorizationService.auth(login, password);
    }




    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public User test(@RequestParam(name = "login") String login,
                     @RequestParam(name = "password") String password) {
        System.out.println("IN THE TEST CONTROLLER");
        // authentificateService.authentificate(password, login);
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());


        return new User();
    }




    @Autowired
    UserPostgreDAO userPostgreDAO;

    @Autowired
    AuthenticationManagerBuilder authenticationManagerBuilder;




    @Autowired
    UserBooksPostgreDAO userBooksPostgreDAO;

    @Autowired
    HttpSession httpSession;





    @Autowired
    AuthenticationManagerBuilder authenticationManagerBuilder2;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user").password("{noop}secret123").roles("USER");
    }

    @RequestMapping(value = "/testget", method = RequestMethod.GET)
    public User testGet() {
        System.out.println("IN THE GEST CONTROLLER!!!");
        return new User();
    }

    @RequestMapping(value = "/secret", method = RequestMethod.GET)
    public void testsmth() {
        System.out.println("In the secret controller!!!");
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
        System.out.println(SecurityContextHolder.getContext().getAuthentication().isAuthenticated());
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getCredentials());
    }
}

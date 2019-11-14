package ua.com.nc.nctrainingproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.com.nc.nctrainingproject.models.Author;
import ua.com.nc.nctrainingproject.models.Book;
import ua.com.nc.nctrainingproject.models.User;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.AuthorPostgreDAO;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.BookPostgreDAO;
import ua.com.nc.nctrainingproject.services.AuthorizationService;

import java.util.ArrayList;
import java.util.List;

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
    authorizationService.loginUser();
  }

  @Autowired
  BookPostgreDAO bookPostgreDAO;

  @Autowired
  AuthorPostgreDAO authorPostgreDAO;


//  @RequestMapping(value = "/test", method = RequestMethod.GET)
//  public void test() {
////    for(Author author:authorPostgreDAO.getAllAuthors()){
////      System.out.println(author.getName());
////    }
//
//    authorPostgreDAO.deleteAuthorById(1);
//
//   // System.out.println(authorPostgreDAO.getAuthorById(1).getBooks().size());
//
//  }

}

package ua.com.nc.nctrainingproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.com.nc.nctrainingproject.models.User;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.UserPostgreDAO;
import ua.com.nc.nctrainingproject.services.RecoverService;

@RequestMapping("/recover")
@RestController
@CrossOrigin
public class RecoverController {

  @Autowired
  UserPostgreDAO userPostgreDAO;

  @Autowired
  RecoverService recoverService;


  // Here i test that controller receive request from angular
  @PostMapping
  public void recover(
    @RequestParam(name = "email") String email) {
    User user = new User("TestUser", "d", email);
    userPostgreDAO.createUser(user);
  }


}

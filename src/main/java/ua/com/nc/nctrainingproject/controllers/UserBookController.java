package ua.com.nc.nctrainingproject.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.com.nc.nctrainingproject.services.UserBookService;

@RestController
@CrossOrigin
public class UserBookController {

  private  final UserBookService userBookService;

  @Autowired
  public UserBookController(UserBookService userBookService) {
    this.userBookService = userBookService;
  }

  @RequestMapping(value = "userBook", method = RequestMethod.POST)
  public void addBookToUser(@RequestParam(name = "user") int userId,
                      @RequestParam(name  = "book") int bookId) {
    userBookService.addBookToUser(userId,bookId);
  }
}



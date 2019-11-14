package ua.com.nc.nctrainingproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.nc.nctrainingproject.models.User;
import ua.com.nc.nctrainingproject.persistance.dao.BookSqlDAO;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.UserPostgreDAO;

@Service
public class AuthorizationService {
  private final UserPostgreDAO userPostgreDAO;
  private final BookSqlDAO bookSqlDAO;

  @Autowired
  public AuthorizationService(UserPostgreDAO userPostgreDAO, BookSqlDAO bookSqlDAO) {
    this.userPostgreDAO = userPostgreDAO;
    this.bookSqlDAO = bookSqlDAO;
  }

  public User auth(String login, String password) {
    User user = userPostgreDAO.getUserByUserName(login);

    if (user != null) {
      return user.getUserPassword().equals(password) ? user : null;
    }
    return null;
  }

  public User register(String login, String password, String email) {
    User user = userPostgreDAO.getUserByUserName(login);

//    if (user == null) {
//      user = new User(login, password, email);
//      userPostgreDAO.createUser(user);
//      return user;
//    }
    return null;
  }




  public void loginUser() {
//    System.out.println(userPostgreDAO.getUserByUserName("first").getUserName());
//    System.out.println(userPostgreDAO.getUserById(1).getUserName());
//    for (User user : userPostgreDAO.getAllUsers()) {
//      System.out.println(user.getUserName());
//    }
//    System.out.println(userPostgreDAO.getUserEmailByUserName("first","dimaskorohodov3@gmail.com"));
    //  User user = new User("NEW", "NEW", "NEW");
    //userPostgreDAO.updateUserByName("first", user);

    User user = bookSqlDAO.getEntityById(2);
    System.out.println(user.getUserName());

  }

}

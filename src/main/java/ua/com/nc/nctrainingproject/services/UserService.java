package ua.com.nc.nctrainingproject.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.nc.nctrainingproject.models.User;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.UserPostgreDAO;

@Service
public class UserService {
  private final UserPostgreDAO userPostgreDAO;
  @Autowired
  UserService(UserPostgreDAO userPostgreDAO){this.userPostgreDAO = userPostgreDAO;}

  public User updateByName(String userName, User user){
    userPostgreDAO.updateUserByName(userName, user);
    return userPostgreDAO.getUserByUserName(user.getUserName());
  }
}

package ua.com.nc.nctrainingproject.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.UserPostgreDAO;

@Service
public class RecoverService {

  @Autowired
  UserPostgreDAO userPostgreDAO;

  public boolean check(String login, String email) {
    if (userPostgreDAO.getUserByUserName(login) == null) {
      return false;
    }
    return true;
  }

}

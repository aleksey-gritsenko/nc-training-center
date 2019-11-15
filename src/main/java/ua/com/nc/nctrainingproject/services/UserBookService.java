package ua.com.nc.nctrainingproject.services;

import org.springframework.stereotype.Service;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.UserBookPostgreDAO;

@Service
public class UserBookService {
  private final UserBookPostgreDAO userBookPostgreDAO;

  public UserBookService(UserBookPostgreDAO userBookPostgreDAO) {
    this.userBookPostgreDAO = userBookPostgreDAO;
  }

  public void addBook(int userId, int bookId) {
    userBookPostgreDAO.addBook(userId, bookId);
  }
}



package ua.com.nc.nctrainingproject.services;

import org.springframework.stereotype.Service;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.UserBooksPostgreDAO;

@Service
public class UserBookService {
	private final UserBooksPostgreDAO userBooksPostgreDAO;

	public UserBookService(UserBooksPostgreDAO userBooksPostgreDAO) {
		this.userBooksPostgreDAO = userBooksPostgreDAO;
	}

	public void addBookToUser(int userId, int bookId) {
		userBooksPostgreDAO.addBookToUser(userId, bookId);
	}
}



package ua.com.nc.nctrainingproject.services;

import org.springframework.stereotype.Service;
import ua.com.nc.nctrainingproject.models.Book;
import ua.com.nc.nctrainingproject.models.UserBook;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.UserBooksPostgreDAO;

import java.util.List;

@Service
public class UserBookService {
	private final UserBooksPostgreDAO userBooksPostgreDAO;
	private final ActionService actionService;

	public UserBookService(UserBooksPostgreDAO userBooksPostgreDAO, ActionService actionService) {
		this.userBooksPostgreDAO = userBooksPostgreDAO;
		this.actionService = actionService;
	}


	public UserBook addBookToUser(UserBook userBook) {
		actionService.addNewAction(userBook.getUserId(), 3);
		List<Book> books = this.getAllUserBooks(userBook.getUserId());
		if (books.stream()
				.filter(book -> book.getId() == userBook.getBookId())
				.findFirst()
				.orElse(null) == null) {
			userBooksPostgreDAO.addBookToUser(userBook);
			return userBook;
		}
		return null;
	}

	public UserBook getUserBookByBookUserId(UserBook userBook) {
		return userBooksPostgreDAO.getUserBookByBookUserId(userBook.getUserId(), userBook.getBookId());
	}

	public List<Book> getAllUserBooks(int userId) {
		return userBooksPostgreDAO.getAllUserBooks(userId);
	}

	public UserBook markBookAsRead(UserBook userBook) {
		actionService.addNewAction(userBook.getUserId(), 5);
		userBooksPostgreDAO.markBookAsRead(userBook.getUserId(), userBook.getBookId());
		return userBook;
	}

	public UserBook markBookAsFavourite(UserBook userBook) {
		actionService.addNewAction(userBook.getUserId(), 4);
		userBooksPostgreDAO.markBookAsFavourite(userBook.getUserId(), userBook.getBookId());
		return userBook;
	}

	public UserBook removeFromRead(UserBook userBook) {
		userBooksPostgreDAO.removeFromRead(userBook.getUserId(), userBook.getBookId());
		return userBook;
	}

	public UserBook removeFromFavourite(UserBook userBook) {
		userBooksPostgreDAO.removeFromFavourite(userBook.getUserId(), userBook.getBookId());
		return userBook;
	}

	public UserBook deleteBookFromAdded(UserBook userBook) {
		userBooksPostgreDAO.deleteFromAdded(userBook.getUserId(), userBook.getBookId());
		return userBook;
	}

	public List<Book> getAllFavouriteBooks(int userId) {
		return userBooksPostgreDAO.getAllFavouriteBooks(userId);
	}

	public List<Book> getAllReadBooks(int userId) {
		return userBooksPostgreDAO.getAllReadBooks(userId);
	}
}



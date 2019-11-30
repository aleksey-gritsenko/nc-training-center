package ua.com.nc.nctrainingproject.services;

import org.springframework.stereotype.Service;
import ua.com.nc.nctrainingproject.models.Book;
import ua.com.nc.nctrainingproject.models.UserBook;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.UserBooksPostgreDAO;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserBookService {
	private final UserBooksPostgreDAO userBooksPostgreDAO;

	public UserBookService(UserBooksPostgreDAO userBooksPostgreDAO) {
		this.userBooksPostgreDAO = userBooksPostgreDAO;
	}


	public UserBook addBookToUser(UserBook userBook) {
		List<Book> books = this.getAllUserBooks(userBook);
		if(books.stream()
				.filter(book->book.getId()==userBook.getBookId())
				.findFirst()
				.orElse(null)==null){
			userBooksPostgreDAO.addBookToUser(userBook);
			return userBook;
		}
		return null;
	}

	public List<Book> getAllUserBooks(UserBook userBook){
		return userBooksPostgreDAO.getAllUserBooks(userBook.getUserId());
	}

	public UserBook markBookAsRead(UserBook userBook) {
		userBooksPostgreDAO.markBookAsRead(userBook.getUserId(), userBook.getBookId());
		return userBook;
	}

	public UserBook markBookAsFavourite(UserBook userBook) {
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

	public List<Book> getAllFavouriteBooks(UserBook userBook) {
		return userBooksPostgreDAO.getAllFavouriteBooks(userBook.getUserId());
	}

	public List<Book> getAllReadBooks(UserBook userBook) {
		return userBooksPostgreDAO.getAllReadBooks(userBook.getUserId());
	}
}



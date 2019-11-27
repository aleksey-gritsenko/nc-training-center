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
		List<Book> books = new ArrayList<>();
		books = this.getAllUserBooks(userBook.getUserId());
		if(books.stream()
				.filter(book->book.getId()==userBook.getBookId())
				.findFirst()
				.orElse(null)==null){
			userBooksPostgreDAO.addBookToUser(userBook);
			return userBook;
		}
		return null;
	}
	public List<Book> getAllUserBooks(int userId){
		return userBooksPostgreDAO.getAllUserBooks(userId);
	}

}



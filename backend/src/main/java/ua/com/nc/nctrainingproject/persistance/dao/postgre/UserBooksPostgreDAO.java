package ua.com.nc.nctrainingproject.persistance.dao.postgre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.com.nc.nctrainingproject.models.Book;
import ua.com.nc.nctrainingproject.models.UserBook;
import ua.com.nc.nctrainingproject.persistance.dao.AbstractDAO;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.UserBooksQuery;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserBooksPostgreDAO extends AbstractDAO {
	private final BookPostgreDAO bookPostgreDAO;

	@Autowired
	public UserBooksPostgreDAO(DataSource dataSource, BookPostgreDAO bookPostgreDAO) {
		super(dataSource);
		this.bookPostgreDAO = bookPostgreDAO;
	}

	public void addBookToUser(UserBook userBook) {
		jdbcTemplate.update(UserBooksQuery.ADD_BOOK_TO_USER, userBook.getUserId(),
				userBook.getBookId(), userBook.getRead(), userBook.getFavorite());
	}

	public List<Book> getAllUserBooks(int userId) {
		List<Book> books = new ArrayList<>();

		for (int id : jdbcTemplate.queryForList(UserBooksQuery.GET_ALL_USER_BOOKS_ID, Integer.class, userId)) {
			books.add(bookPostgreDAO.getBookById(id));
		}
		return books;
	}

	public List<Book> getUserBookList(int userId) {
		List<Book> books = new ArrayList<>();

		for (int id : jdbcTemplate.queryForList(UserBooksQuery.GET_ALL_USER_BOOKS, Integer.class, userId)) {
			books.add(bookPostgreDAO.getBookById(id));
		}
		return books;
	}

	public List<Book> getAllFavouriteBooks(int userId) {
		List<Book> books = new ArrayList<>();

		for (int id : jdbcTemplate.queryForList(UserBooksQuery.GET_ALL_FAVOURITE_BOOKS_ID, Integer.class, userId)) {
			books.add(bookPostgreDAO.getBookById(id));
		}
		return books;
	}

	public List<Book> getAllReadBooks(int userId) {
		List<Book> books = new ArrayList<>();

		for (int id : jdbcTemplate.queryForList(UserBooksQuery.GET_ALL_READ_BOOKS_ID, Integer.class, userId)) {
			books.add(bookPostgreDAO.getBookById(id));
		}
		return books;
	}

	public void markBookAsRead(int userId, int bookId) {
		update(UserBooksQuery.MARK_BOOK_AS_READ, new Object[]{userId, bookId});
	}

	public void markBookAsFavourite(int userId, int bookId) {
		update(UserBooksQuery.MARK_BOOK_AS_FAVOURITE, new Object[]{userId, bookId});
	}

	public void removeFromRead(int userId, int bookId) {
		update(UserBooksQuery.REMOVE_FROM_READ, new Object[]{userId, bookId});
	}

	public void removeFromFavourite(int userId, int bookId) {
		update(UserBooksQuery.REMOVE_FROM_FAVOURITE, new Object[]{userId, bookId});
	}

	public void deleteFromAdded(int userId, int bookId) {
		update(UserBooksQuery.DELETE_BOOK_FROM_ADDED, new Object[]{userId, bookId});
	}
}

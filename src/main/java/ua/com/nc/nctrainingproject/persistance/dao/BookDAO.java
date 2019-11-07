package ua.com.nc.nctrainingproject.persistance.dao;

import ua.com.nc.nctrainingproject.models.Book;

import java.util.List;

public interface BookDAO {

	Book getBookByTitle(String title);

	Book getBookByAuthor(String author);

	Book getBookByStatus(String status);

	void updateBook(int book_id, String title, String header,
					String author, String overview,
					String status, int photoId, int fileId);

	void createBook(Book book);

	List<Book> getAllBooks();
}

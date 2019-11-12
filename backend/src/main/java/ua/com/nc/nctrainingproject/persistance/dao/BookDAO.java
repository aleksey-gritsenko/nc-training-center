package ua.com.nc.nctrainingproject.persistance.dao;

import ua.com.nc.nctrainingproject.models.Book;

import java.util.List;

public interface BookDAO {

	Book getBookById(int bookId);

	List<Book> getBooksByTitle(String title);

	List<Book> getBooksByAuthor(String author);

	List<Book> getBooksByStatus(String status);

	void updateBook(Book book, int bookId);

	void createBook(Book book);

	List<Book> getAllBooks();
}

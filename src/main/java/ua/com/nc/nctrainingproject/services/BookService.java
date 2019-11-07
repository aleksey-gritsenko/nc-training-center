package ua.com.nc.nctrainingproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.nc.nctrainingproject.models.Book;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.BookPostgreDAO;

@Service
public class BookService {

	private final BookPostgreDAO bookPostgreDAO;

	@Autowired
	public BookService(BookPostgreDAO bookPostgreDAO) {
		this.bookPostgreDAO = bookPostgreDAO;
	}

	public Book createBook(String title, String header, String author, String overview,
						   String status, int photoId, int fileId) {
		Book book = new Book(title, header, author, overview, status, photoId, fileId);
		bookPostgreDAO.createBook(book);

		return book;
	}
}

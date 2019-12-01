package ua.com.nc.nctrainingproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.nc.nctrainingproject.models.Author;
import ua.com.nc.nctrainingproject.models.Book;
import ua.com.nc.nctrainingproject.models.Genre;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.AuthorBookPostgreDAO;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.AuthorPostgreDAO;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.BookPostgreDAO;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.GenrePostgreDAO;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.FilterCriterionQuery;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
	private final BookPostgreDAO bookPostgreDAO;
	private final FilterCriterionQuery filterCriterionQuery;
	private final GenrePostgreDAO genrePostgreDAO;
	private final AuthorPostgreDAO authorPostgreDAO;
	private final AuthorBookPostgreDAO authorBookPostgreDAO;

	@Autowired
	public BookService(BookPostgreDAO bookPostgreDAO, FilterCriterionQuery filterCriterionQuery, GenrePostgreDAO genrePostgreDAO, AuthorPostgreDAO authorPostgreDAO, AuthorBookPostgreDAO authorBookPostgreDAO) {
		this.bookPostgreDAO = bookPostgreDAO;
		this.filterCriterionQuery = filterCriterionQuery;
		this.genrePostgreDAO = genrePostgreDAO;
		this.authorPostgreDAO = authorPostgreDAO;
		this.authorBookPostgreDAO = authorBookPostgreDAO;
	}

	public Book createBook(Book book) {
		if (bookPostgreDAO.getBookById(book.getId()) == null) {
			bookPostgreDAO.createBook(book);
		}

		return book;
	}

	public Book getBookById(int bookId) {
		return bookPostgreDAO.getBookById(bookId);
	}

	public Book updateBook(Book book) {
		if (bookPostgreDAO.getBookById(book.getId()) != null) {
			bookPostgreDAO.updateBookById(book.getId(), book);
			return book;
		} else {
			return null;
		}
	}

	public List<Book> getAllBooks() {
		return bookPostgreDAO.getAllBooks();
	}

	public List<Book> filterBooks(String header, ArrayList<String> genre,
								  ArrayList<String> author) {
		filterCriterionQuery.setAuthor(author);
		filterCriterionQuery.setGenre(genre);
		filterCriterionQuery.setHeader(header);
		return bookPostgreDAO.filterBooks(filterCriterionQuery);
	}
//bbb
	public List<Author> getAllAuthors() {return authorPostgreDAO.getAllAuthors();}
	public List<Author> getAuthorsByBookId(int bookId){return authorBookPostgreDAO.getAuthorsByBookId(bookId);}
	public List<Genre> getAllGenres() {return genrePostgreDAO.getAllGenres();}
	public Genre getGenreByBookId(int bookId)  {return bookPostgreDAO.getGenreByBookId(bookId);}
}

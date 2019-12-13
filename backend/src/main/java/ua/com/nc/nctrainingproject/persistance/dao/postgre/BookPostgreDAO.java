package ua.com.nc.nctrainingproject.persistance.dao.postgre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.com.nc.nctrainingproject.models.Author;
import ua.com.nc.nctrainingproject.models.Book;
import ua.com.nc.nctrainingproject.models.Genre;
import ua.com.nc.nctrainingproject.persistance.dao.AbstractDAO;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.BookQuery;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.FilterCriterionQuery;
import ua.com.nc.nctrainingproject.persistance.mappers.BookRowMapper;
import ua.com.nc.nctrainingproject.persistance.mappers.GenreMapper;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class BookPostgreDAO extends AbstractDAO<Book> {

	private final AuthorBookPostgreDAO authorBookPostgreDAO;
	private final GenrePostgreDAO genrePostgreDAO;
	private final AuthorPostgreDAO authorPostgreDAO;

	@Autowired
	public BookPostgreDAO(DataSource dataSource, AuthorBookPostgreDAO authorBookPostgreDAO,
						  GenrePostgreDAO genrePostgreDAO, AuthorPostgreDAO authorPostgreDAO) {
		super(dataSource);
		this.authorBookPostgreDAO = authorBookPostgreDAO;
		this.genrePostgreDAO = genrePostgreDAO;
		this.authorPostgreDAO = authorPostgreDAO;
	}

	public Book getBookById(int bookId) {
		return super.getEntityById(BookQuery.GET_BOOK, new BookRowMapper(), bookId);
	}

	public List<Book> getAllBooks() {
		return super.getAllEntities(BookQuery.GET_ALL, new BookRowMapper());
	}

	public void deleteBookById(int bookId) {
		super.deleteEntityById(BookQuery.DELETE_BOOK_BY_ID, bookId);
	}


	public void createBook(Book book) {
		jdbcTemplate.update(BookQuery.CREATE_BOOK, book.getHeader(), book.getOverview(), book.getFileId(),
				book.getStatus(), genrePostgreDAO.getIdByGenre(book.getGenre()), book.getPhotoId());
		book.setId(getAllBooks().get(getAllBooks().size() - 1).getId());
		for (Author author : book.getAuthors()) {
			if (authorPostgreDAO.getAuthorByName(author.getName()).size() == 0) {
				authorPostgreDAO.createAuthor(author);
			}
			authorBookPostgreDAO.createAuthorBookConnection(book.getId(),
					authorPostgreDAO.getAuthorByName(author.getName()).get(0).getId());

		}
	}

	public void updateBookById(int id, Book book) {
		Object[] params = new Object[]{book.getHeader(), book.getOverview(), book.getFileId(), book.getStatus(),
				genrePostgreDAO.getIdByGenre(book.getGenre()), book.getPhotoId(), id};
		super.updateEntityById(id, params, BookQuery.UPDATE_BOOK);
	}


	public List<Book> filterBooks(FilterCriterionQuery filterCriterionQuery) {
		String query = filterCriterionQuery.makeQuery();
		Object[] args = filterCriterionQuery.makeArrayArgsStream();
		List<Book> books = jdbcTemplate.query(query, args, new BookRowMapper());
		for (Book book : books) {
			book.setAuthors(authorBookPostgreDAO.getAuthorsByBookId(book.getId()));
		}
		return books;
	}

	public Genre getGenreByBookId(int bookId) {
		List<Genre> genres = jdbcTemplate.query(BookQuery.GET_GENRE_BY_BOOK_ID, new GenreMapper(), bookId);
		if (genres.size() == 0) {
			return null;
		}
		return genres.get(0);
	}

	public List<Book> getMostRatedBooks() {
		List<Book> books = jdbcTemplate.query(BookQuery.GET_MOST_RATED_BOOKS, new BookRowMapper());
		for (Book book : books) {
			book.setAuthors(authorBookPostgreDAO.getAuthorsByBookId(book.getId()));
			book.setGenre(genrePostgreDAO.getGenreById(book.getId()));
		}
		return books;
	}
}


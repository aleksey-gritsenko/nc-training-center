package ua.com.nc.nctrainingproject.persistance.dao.postgre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.com.nc.nctrainingproject.models.Book;
import ua.com.nc.nctrainingproject.persistance.dao.BookDAO;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.BookQuery;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.FilterCriterionQuery;
import ua.com.nc.nctrainingproject.persistance.mappers.BookRowMapper;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
public class BookPostgreDAO implements BookDAO {

	private final JdbcTemplate jdbcTemplate;
	private final FilterCriterionQuery filterCriterionQuery;

	@Autowired
	public BookPostgreDAO(JdbcTemplate jdbcTemplate,FilterCriterionQuery filterCriterionQuery) {
		this.jdbcTemplate = jdbcTemplate;
		this.filterCriterionQuery =filterCriterionQuery;
	}

	@Override
	public Book getBookById(int bookId) {
		try {
			return jdbcTemplate.queryForObject(BookQuery.GET_BOOK_BY_ID, new Object[]{bookId}, new BookRowMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<Book> getBooksByTitle(String title) {
		try {
			return jdbcTemplate.query(BookQuery.GET_BOOKS_BY_TITLE, new Object[]{title}, new BookRowMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<Book> getBooksByAuthor(String author) {
		try {
			return jdbcTemplate.query(BookQuery.GET_BOOKS_BY_AUTHOR, new Object[]{author}, new BookRowMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<Book> getBooksByStatus(String status) {
		try {
			return jdbcTemplate.query(BookQuery.GET_BOOKS_BY_STATUS, new Object[]{status}, new BookRowMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public void createBook(Book book) {
		jdbcTemplate.update(BookQuery.CREATE_BOOK,
				book.getTitle(),
				book.getHeader(),
				book.getAuthor(),
				book.getOverview(),
				book.getPhotoId(),
				book.getFileId(),
				book.getStatus());
	}

	public void updateBook(int bookId, String title, String header,
						   String author, String overview,
						   String status, int photoId, int fileId) {
		jdbcTemplate.update(BookQuery.UPDATE_BOOK, title, header, author, overview, status, photoId, fileId, bookId);
	}

	@Override
	public void updateBook(Book book, int bookId) {
		jdbcTemplate.update(BookQuery.UPDATE_BOOK, book.getTitle(), book.getHeader(), book.getAuthor(),
				book.getOverview(), book.getPhotoId(), book.getFileId(), book.getStatus(), bookId);
	}


	@Override
	public List<Book> getAllBooks() {
		return jdbcTemplate.query(BookQuery.GET_ALL_BOOKS, new BookRowMapper());
	}
	public List<Book> filterBooks
    (FilterCriterionQuery filterCriterionQuery){
	  return jdbcTemplate.query(BookQuery.JOIN_BOOKS_ANNOUNCEMENT+filterCriterionQuery.getConditionsQuery()
      ,new BookRowMapper(),filterCriterionQuery.
        getSqlCriteriaMap().get(BookQuery.CONDITIONS_NAME),
      filterCriterionQuery.getSqlCriteriaMap().get(BookQuery.CONDITIONS_GENRES),
      filterCriterionQuery.getSqlCriteriaMap().get(BookQuery.CONDITIONS_GENRES)
    );
  }
	public String makeQuery(String name, String genre, String author, Date dateFrom,Date dateTo){
    String query = BookQuery.JOIN_BOOKS_ANNOUNCEMENT;
	  /* String query = BookQuery.JOIN_BOOKS_ANNOUNCEMENT;
	  if(genre !=null ){
	    query =query +BookQuery.CONDITIONS_GENRES;
    }
    query = query+" OR ";
	  if(author !=null){
	    query =query+ BookQuery.CONDITION_AUTHOR;
    }
    query = query+" OR ";

    if(name!=null){
	    query= query + BookQuery.CONDITIONS_NAME;
    }
    query = query+" OR ";
	  if( dateFrom != null && dateTo !=null){
	    query = query + BookQuery.CONDITION_ANNOUNCEMENT_DATE;
    }
    query = query+";";
	  return query;*/

	 Set<String> keys =filterCriterionQuery.getSqlCriteriaMap().keySet();
    for (String key:keys ) {
      query = query+key;

    }
    return query;
  }
}

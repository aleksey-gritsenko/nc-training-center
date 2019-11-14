package ua.com.nc.nctrainingproject.persistance.dao.postgre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.com.nc.nctrainingproject.models.Author;
import ua.com.nc.nctrainingproject.models.Book;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.AuthorBookQuery;
import ua.com.nc.nctrainingproject.persistance.mappers.AuthorMapper;
import ua.com.nc.nctrainingproject.persistance.mappers.BookRowMapper;

import java.util.List;

@Repository
public class AuthorBookPostgreDAO {

  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public AuthorBookPostgreDAO(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<Book> getBooksByAuthorId(int id) {
    return jdbcTemplate.query(AuthorBookQuery.GET_ALL_BOOKS_BY_AUTHOR_ID, new BookRowMapper(), id);
  }

  public List<Author> getAuthorsByBookId(int id) {
    return jdbcTemplate.query(AuthorBookQuery.GET_ALL_AUTHORS_BY_BOOK_ID, new AuthorMapper(), id);
  }

  public void createAuthorBookConnection(int bookId, int authorId) {
    jdbcTemplate.update(AuthorBookQuery.CREATE, bookId, authorId);
  }

  public void deleteBooksById(int bookId) {
    jdbcTemplate.update(AuthorBookQuery.DELETE_BOOKS_BY_ID, bookId);
  }

  public void deleteAuthorsById(int authorId) {
    jdbcTemplate.update(AuthorBookQuery.DELETE_AUTHORS_BY_ID, authorId);
  }

}

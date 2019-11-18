package ua.com.nc.nctrainingproject.persistance.dao.postgre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.com.nc.nctrainingproject.models.Book;
import ua.com.nc.nctrainingproject.persistance.dao.AbstractDAO;
import ua.com.nc.nctrainingproject.persistance.dao.UserBooksDAO;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.UserBooksQuery;
import ua.com.nc.nctrainingproject.persistance.mappers.BookRowMapper;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserBooksPostgreDAO extends AbstractDAO implements UserBooksDAO {

  private final BookPostgreDAO bookPostgreDAO;

  @Autowired
  public UserBooksPostgreDAO(JdbcTemplate jdbcTemplate, BookPostgreDAO bookPostgreDAO) {
    super(jdbcTemplate);
    this.bookPostgreDAO = bookPostgreDAO;
  }

  @Override
  public void addBookToUser(int userId, int bookId) {
    create(UserBooksQuery.ADD_BOOK_TO_USER, new Object[]{userId, bookId});
  }

  @Override
  public List<Book> getAllUserBooks(int userId) {
    List<Book> books = new ArrayList<>();

    for (int id : jdbcTemplate.queryForList(UserBooksQuery.GET_ALL_USER_BOOKS_ID, Integer.class, userId)) {
      books.add(bookPostgreDAO.getBookById(id));
    }
    return books;
  }

  @Override
  public List<Book> getAllFavouriteBooks(int userId) {
    List<Book> books = new ArrayList<>();

    for (int id : jdbcTemplate.queryForList(UserBooksQuery.GET_ALL_FAVOURITE_BOOKS_ID, Integer.class, userId)) {
      books.add(bookPostgreDAO.getBookById(id));
    }
    return books;
  }

  @Override
  public List<Book> getAllReadBooks(int userId) {
    List<Book> books = new ArrayList<>();

    for (int id : jdbcTemplate.queryForList(UserBooksQuery.GET_ALL_READ_BOOKS_ID, Integer.class, userId)) {
      books.add(bookPostgreDAO.getBookById(id));
    }
    return books;
  }

  @Override
  public void markBookAsRead(int userId, int bookId) {
    update(UserBooksQuery.MARK_BOOK_AS_READ, new Object[]{userId, bookId});
  }

  @Override
  public void markBookAsFavourite(int userId, int bookId) {
    update(UserBooksQuery.MARK_BOOK_AS_FAVOURITE, new Object[]{userId, bookId});
  }

  @Override
  public void removeFromRead(int userId, int bookId) {
    update(UserBooksQuery.REMOVE_FROM_READ, new Object[]{userId, bookId});
  }

  @Override
  public void removeFromFavourite(int userId, int bookId) {
    update(UserBooksQuery.REMOVE_FROM_FAVOURITE, new Object[]{userId, bookId});
  }

}

package ua.com.nc.nctrainingproject.persistance.dao.postgre;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.com.nc.nctrainingproject.persistance.dao.UserBookDAO;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.UserBookQuery;

@Repository
public class UserBookPostgreDAO  implements UserBookDAO {

  private final JdbcTemplate jdbcTemplate;
  @Autowired
  public UserBookPostgreDAO(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public void addBook(int userId, int bookId) {
    jdbcTemplate.update(UserBookQuery.ADD_BOOK, userId,bookId);
  }
}


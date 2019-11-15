package ua.com.nc.nctrainingproject.persistance.mappers;

import org.springframework.jdbc.core.RowMapper;
import ua.com.nc.nctrainingproject.models.UserBook;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.UserBookQuery;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserBookRowMapper implements RowMapper<UserBook> {
  @Override
  public UserBook mapRow(ResultSet resultSet, int i) throws SQLException {
    UserBook userBook = new UserBook();
    userBook.setUserId(resultSet.getInt(UserBookQuery.USER_ID));
    userBook.setBookId(resultSet.getInt(UserBookQuery.BOOK_ID));
    userBook.setRead(resultSet.getBoolean(UserBookQuery.IS_READ));
    userBook.setFavorite(resultSet.getBoolean(UserBookQuery.IS_FAVORITE));
    return userBook;
  }
}


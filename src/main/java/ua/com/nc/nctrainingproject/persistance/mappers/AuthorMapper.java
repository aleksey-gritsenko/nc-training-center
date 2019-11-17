package ua.com.nc.nctrainingproject.persistance.mappers;

import org.springframework.jdbc.core.RowMapper;
import ua.com.nc.nctrainingproject.models.Author;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.AuthorQuery;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorMapper implements RowMapper<Author> {

  @Override
  public Author mapRow(ResultSet resultSet, int i) throws SQLException {
    Author author = new Author();
    author.setId(resultSet.getInt(AuthorQuery.ID));
    author.setName(resultSet.getString(AuthorQuery.AUTHOR_NAME));
    return author;
  }
}

package ua.com.nc.nctrainingproject.persistance.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ua.com.nc.nctrainingproject.models.User;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.UserQuery;
import ua.com.nc.nctrainingproject.persistance.mappers.UserRowMapper;

@Repository
public class BookSqlDAO extends AbstractDAO<User> {

  @Autowired
  public BookSqlDAO(JdbcTemplate jdbcTemplate) {
    super(jdbcTemplate);
  }

  public void create(User user) {
    //   create(UserQuery.CREATE_USER, );

  }


  public User getEntityById(int id) {
    return super.getEntityById(UserQuery.GET_BY_ID, new UserRowMapper(), id);
  }

}

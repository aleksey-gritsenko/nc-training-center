package ua.com.nc.nctrainingproject.persistance.dao.postgre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.com.nc.nctrainingproject.models.User;
import ua.com.nc.nctrainingproject.persistance.dao.AbstractDAO;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.UserQuery;
import ua.com.nc.nctrainingproject.persistance.mappers.UserRowMapper;

import java.util.List;

@Repository
public class UserPostgreDAO extends AbstractDAO<User> {

  @Autowired
  public UserPostgreDAO(JdbcTemplate jdbcTemplate) {
    super(jdbcTemplate);

  }

  public User getUserByEmail(String email) {
    List<User> users = jdbcTemplate.query(UserQuery.GET_BY_EMAIL, new UserRowMapper(), email);
    if (users.size() == 0) {
      return null;
    }
    return users.get(0);
  }


  public User getUserById(int id) {
    List<User> users = jdbcTemplate.query(UserQuery.GET_BY_ID, new UserRowMapper(), id);
    if (users.size() == 0) {
      return null;
    }
    return users.get(0);
  }

  public User getUserByUserName(String userName) {
    List<User> users = jdbcTemplate.query(UserQuery.GET_BY_USERNAME, new UserRowMapper(), userName);
    if (users.size() == 0) {
      return null;
    }
    return users.get(0);
  }

  public void updateUserByName(String userName, User user) {
    int rows = jdbcTemplate.update(UserQuery.UPDATE_BY_USERNAME,
      user.getUserName(),
      user.getUserPassword(),
      user.getEmail(),
      userName);
  }

  public List<User> getAllUsers() {
    return jdbcTemplate.query(UserQuery.GET_ALL, new UserRowMapper());
  }

  public String getUserEmailByUserName(String userName, String email) {

    List<User> result = jdbcTemplate.query(UserQuery.GET_EMAIL_BY_USERNAME,
      new UserRowMapper(), userName, email);
    if (result.size() == 0) {
      return null;
    }
    return result.get(0).getEmail();
  }

  public void updatePassword(String password, String userName) {
    jdbcTemplate.update(UserQuery.UPDATE_PASSWORD, password, userName);
  }

  public void createUser(User user) {
    jdbcTemplate.update(UserQuery.CREATE_USER, user.getUserName(), user.getUserPassword(), user.getEmail());
  }


//  public List<User> get


}

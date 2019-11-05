package ua.com.nc.nctrainingproject.persistance.dao.postgre;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.com.nc.nctrainingproject.models.User;
import ua.com.nc.nctrainingproject.persistance.dao.UserDAO;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.UserQuery;
import ua.com.nc.nctrainingproject.persistance.mappers.UserRowMapper;

import java.util.List;

@Repository
public class UserPostgreDAO implements UserDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserPostgreDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User getUserByUserName(String userName) {

        try {
            return jdbcTemplate.queryForObject(UserQuery.GET_BY_USERNAME, new Object[]{userName}, new UserRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void updatePassword(String password, String userName) {
        jdbcTemplate.update(UserQuery.UPDATE_PASSWORD, password, userName);
    }

    @Override
    public void createUser(User user) {
        jdbcTemplate.update(UserQuery.CREATE_USER, user.getUserName(), user.getUserPassword(), user.getEmail());
    }

    @Override
    public List<User> getAllUsers() {
        return jdbcTemplate.query(UserQuery.GET_ALL, new UserRowMapper());
    }

}

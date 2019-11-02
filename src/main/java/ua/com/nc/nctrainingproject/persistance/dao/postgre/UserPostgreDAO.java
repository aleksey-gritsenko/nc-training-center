package ua.com.nc.nctrainingproject.persistance.dao.postgre;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.com.nc.nctrainingproject.model.User;
import ua.com.nc.nctrainingproject.persistance.dao.UserDAO;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.UserQuery;
import ua.com.nc.nctrainingproject.persistance.mappers.UserRowMapper;

import java.util.List;

@Repository
public class UserPostgreDAO implements UserDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public User getUserByUserName(String userName) {
        return jdbcTemplate.queryForObject(UserQuery.GET_BY_USERNAME, new Object[]{userName}, new UserRowMapper());
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

package ua.com.nc.nctrainingproject.persistance.mappers;


import org.springframework.jdbc.core.RowMapper;
import ua.com.nc.nctrainingproject.model.User;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.UserQuery;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setUserName(resultSet.getString(UserQuery.USERNAME));
        user.setUserPassword(resultSet.getString(UserQuery.USERPASSWORD));
        user.setEmail(resultSet.getString(UserQuery.EMAIL));
        return user;
    }
}

package ua.com.nc.nctrainingproject.persistance.mappers;

import org.springframework.jdbc.core.RowMapper;
import ua.com.nc.nctrainingproject.models.User;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.UserQuery;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet resultSet, int i) throws SQLException {
		User user = new User();
		user.setId(resultSet.getInt(UserQuery.ID));
		user.setUserName(resultSet.getString(UserQuery.USERNAME));
		user.setUserPassword(resultSet.getString(UserQuery.USER_PASSWORD));
		user.setEmail(resultSet.getString(UserQuery.EMAIL));
        user.setUserRole(resultSet.getString(UserQuery.ROLE));
		user.setVerified(resultSet.getBoolean(UserQuery.VERIFIED));
		user.setActivated(resultSet.getBoolean(UserQuery.ACTIVATED));

		return user;
	}
}

package ua.com.nc.nctrainingproject.persistance.mappers;

import org.springframework.jdbc.core.RowMapper;
import ua.com.nc.nctrainingproject.models.Activity;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.ActivityQuery;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ActivityRowMapper implements RowMapper<Activity> {
	@Override
	public Activity mapRow(ResultSet resultSet, int i) throws SQLException {
		Activity activity = new Activity();
		activity.setActionId(resultSet.getInt(ActivityQuery.ACTION_ID));
		activity.setUserId(resultSet.getInt(ActivityQuery.USER_ID));

		return activity;
	}
}

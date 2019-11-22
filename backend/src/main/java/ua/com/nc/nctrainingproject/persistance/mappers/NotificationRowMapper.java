package ua.com.nc.nctrainingproject.persistance.mappers;

import org.springframework.jdbc.core.RowMapper;
import ua.com.nc.nctrainingproject.models.Notification;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.NotificationQuery;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NotificationRowMapper implements RowMapper<Notification> {
	@Override
	public Notification mapRow(ResultSet resultSet, int i) throws SQLException {
		Notification notification = new Notification();
		notification.setActionId(resultSet.getInt(NotificationQuery.ACTION_ID));
		notification.setUserId(resultSet.getInt(NotificationQuery.USER_ID));

		return notification;
	}
}

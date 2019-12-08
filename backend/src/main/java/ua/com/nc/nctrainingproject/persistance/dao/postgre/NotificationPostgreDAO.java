package ua.com.nc.nctrainingproject.persistance.dao.postgre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.com.nc.nctrainingproject.models.Notification;
import ua.com.nc.nctrainingproject.persistance.dao.AbstractDAO;
import ua.com.nc.nctrainingproject.persistance.dao.NotificationsDAO;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.NotificationQuery;
import ua.com.nc.nctrainingproject.persistance.mappers.NotificationRowMapper;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class NotificationPostgreDAO extends AbstractDAO<Notification> implements NotificationsDAO {

	@Autowired
	public NotificationPostgreDAO(DataSource dataSource) {
		super(dataSource);
	}

	@Override
	public List<Notification> getNotificationByUserID(int userId) {
		return jdbcTemplate.query(NotificationQuery.GET_BY_USER_ID, new NotificationRowMapper(), userId);
	}

	@Override
	public List<Notification> getNotificationByActionID(int actionId) {
		return jdbcTemplate.query(NotificationQuery.GET_BY_ACTION_ID,
				new NotificationRowMapper(), actionId);
	}

	@Override
	public List<Notification> getNotificationByUserActionID(int userId, int actionId) {
		return jdbcTemplate.query(NotificationQuery.GET_BY_USER_ACTION_ID,
				new NotificationRowMapper(), actionId, userId);
	}

	@Override
	public List<Notification> getAllNotifications() {
		return jdbcTemplate.query(NotificationQuery.GET_ALL_NOTIFICATIONS, new NotificationRowMapper());
	}

	@Override
	public void deleteNotification(int userId) {
		jdbcTemplate.update(NotificationQuery.DELETE_BY_USER_ID, userId);
	}

	@Override
	public void createNotification(Notification notification) {
		jdbcTemplate.update(NotificationQuery.CREATE_NOTIFICATION,
				notification.getActionId(), notification.getUserId());
	}
}

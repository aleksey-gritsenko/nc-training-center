package ua.com.nc.nctrainingproject.persistance.dao;

import ua.com.nc.nctrainingproject.models.Notification;

import java.util.List;

public interface NotificationsDAO {

	List<Notification> getNotificationByUserID(int userId);

	List<Notification> getNotificationByActionID(int actionId);

	List<Notification> getNotificationByUserActionID(int userId, int actionId);

	List<Notification> getAllNotifications();

	void deleteNotification(int userId);

	void createNotification(int userId, int actionId);
}

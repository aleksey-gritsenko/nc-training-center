package ua.com.nc.nctrainingproject.persistance.dao;

import ua.com.nc.nctrainingproject.models.Notification;

import java.util.List;

public interface NotificationsDAO {

	Notification getNotificationByUserID(int userId);

	Notification getNotificationByActionID(int actionId);

	List<Notification> getAllNotifications();

	void deleteNotification(int userId, int actionId);

	void createNotification(Notification notification);
}

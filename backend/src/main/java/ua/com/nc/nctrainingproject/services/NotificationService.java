package ua.com.nc.nctrainingproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.nc.nctrainingproject.models.Action;
import ua.com.nc.nctrainingproject.models.ActionType;
import ua.com.nc.nctrainingproject.models.Notification;
import ua.com.nc.nctrainingproject.models.User;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.ActionPostgreDAO;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.ActionTypePostgreDAO;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.NotificationPostgreDAO;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.UserPostgreDAO;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService {

	private final NotificationPostgreDAO notificationPostgreDAO;
	private final UserPostgreDAO userPostgreDAO;
	private final ActionTypePostgreDAO actionTypePostgreDAO;
	private final ActionPostgreDAO actionPostgreDAO;

	@Autowired
	public NotificationService(NotificationPostgreDAO notificationPostgreDAO, UserPostgreDAO userPostgreDAO,
							   ActionTypePostgreDAO actionTypePostgreDAO, ActionPostgreDAO actionPostgreDAO) {
		this.notificationPostgreDAO = notificationPostgreDAO;
		this.userPostgreDAO = userPostgreDAO;
		this.actionPostgreDAO = actionPostgreDAO;
		this.actionTypePostgreDAO = actionTypePostgreDAO;
	}

	public List<Notification> getAllNotifications() {
		return notificationPostgreDAO.getAllNotifications();
	}

	public Notification createNotification(Notification notification) {
		if (userPostgreDAO.getUserById(notification.getUserId()) == null &&
				notificationPostgreDAO.getNotificationByActionID(notification.getActionId()) == null) {
			notificationPostgreDAO.createNotification(notification.getUserId(), notification.getActionId());
		}
		return notification;
	}

	public Notification deleteNotification(Notification notification) {
		if (userPostgreDAO.getUserById(notification.getUserId()) != null) {
			notificationPostgreDAO.deleteNotification(notification.getUserId());
		}
		return notification;
	}

	public List<Notification> getNotificationByActionID(int actionId) {
		List<String> data = new ArrayList<>();
		List<Notification> notifications = new ArrayList<>();
		if (actionPostgreDAO.getActionById(actionId) != null) {
			notifications = notificationPostgreDAO.getNotificationByActionID(actionId);
		}
		return notifications;
	}

	public List<String> getNotificationByUserID(int userId) {
		List<String> data = new ArrayList<>();
		List<Action> actions = new ArrayList<>();
		List<ActionType> actionTypes = new ArrayList<>();

		if (userPostgreDAO.getUserById(userId) != null) {
			User user = userPostgreDAO.getUserById(userId);
			List<Notification> notifications = notificationPostgreDAO.getNotificationByUserID(userId);

			for (Notification notification : notifications) {
				actions.add(actionPostgreDAO.getActionById(notification.getActionId()));
			}

			for (Action action : actions) {
				actionTypes.add(actionTypePostgreDAO.getActionTypeByActionTypeId(action.getActionTypeId()));
			}
			for (ActionType actionType : actionTypes) {
				if (user.getId() == userId && actionType.getEntity().equals("achievement")) {
					data.add("You " + actionType.getActionName());
				} else {
					data.add(user.getUserName() + " " + actionType.getActionName());
				}
			}
		}
		return data;
	}

	public List<Notification> getNotificationByUserActionID(int userId, int actionId) {
		return notificationPostgreDAO.getNotificationByUserActionID(userId, actionId);
	}
}

package ua.com.nc.nctrainingproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.nc.nctrainingproject.models.Action;
import ua.com.nc.nctrainingproject.models.User;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.ActionPostgreDAO;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.FriendsPostgreDAO;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.NotificationPostgreDAO;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.UserPostgreDAO;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActionService {

	private final ActionPostgreDAO actionPostgreDAO;
	private final UserPostgreDAO userPostgreDAO;
	private final FriendsPostgreDAO friendsPostgreDAO;
	private final NotificationPostgreDAO notificationPostgreDAO;

	@Autowired
	public ActionService(ActionPostgreDAO actionPostgreDAO, UserPostgreDAO userPostgreDAO,
						 FriendsPostgreDAO friendsPostgreDAO, NotificationPostgreDAO notificationPostgreDAO) {
		this.actionPostgreDAO = actionPostgreDAO;
		this.userPostgreDAO = userPostgreDAO;
		this.friendsPostgreDAO = friendsPostgreDAO;
		this.notificationPostgreDAO = notificationPostgreDAO;
	}

	public Action addNewAction(int userId, int actionTypeId){
		Action action = new Action(userId, actionTypeId);
		createAction(action);

		List<User> users = friendsPostgreDAO.getAllFriends(userId);

		for (User user : users) {
			notificationPostgreDAO.createNotification(user.getId(), action.getActionId());
		}
		return action;
	}

	public List<Action> getAllActions() {
		return actionPostgreDAO.getAllActions();
	}

	public Action getActionById(int actionId) {
		return actionPostgreDAO.getActionById(actionId);
	}

	public Action getActionByUserId(int userId) {
		return actionPostgreDAO.getActionByUserId(userId);
	}

	public Action getActionByActionTypeId(int actionTypeId) {
		return actionPostgreDAO.getActionByActionTypeId(actionTypeId);
	}

	public void deleteActionByActionId(int actionId) {
		if (actionPostgreDAO.getActionById(actionId) != null) {
			actionPostgreDAO.deleteActionByActionId(actionId);
		}
	}

	public Action createAction(Action action) {
		actionPostgreDAO.createAction(action);
		return action;
	}

	public void updateAction(int actionId, Action action) {
		if (actionPostgreDAO.getActionById(actionId) != null) {
			actionPostgreDAO.updateAction(actionId, action);
		}
	}
}

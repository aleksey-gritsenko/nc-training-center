package ua.com.nc.nctrainingproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.nc.nctrainingproject.models.Action;
import ua.com.nc.nctrainingproject.models.User;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.ActionPostgreDAO;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.ActionTypePostgreDAO;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.FriendsPostgreDAO;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.ActivityPostgreDAO;

import java.util.List;

@Service
public class ActionService {

	private final ActionPostgreDAO actionPostgreDAO;
	private final FriendsPostgreDAO friendsPostgreDAO;
	private final ActivityPostgreDAO activityPostgreDAO;
	private final ActionTypePostgreDAO actionTypePostgreDAO;

	@Autowired
	public ActionService(ActionPostgreDAO actionPostgreDAO, FriendsPostgreDAO friendsPostgreDAO,
						 ActivityPostgreDAO activityPostgreDAO,
						 ActionTypePostgreDAO actionTypePostgreDAO) {
		this.actionPostgreDAO = actionPostgreDAO;
		this.friendsPostgreDAO = friendsPostgreDAO;
		this.activityPostgreDAO = activityPostgreDAO;
		this.actionTypePostgreDAO = actionTypePostgreDAO;
	}

	public Action addNewAction(int userId, int actionTypeId) {
		createAction(new Action(userId, actionTypeId));

		Action action = actionPostgreDAO.getActionByUserAndTypeId(userId, actionTypeId);

		List<User> users = friendsPostgreDAO.getAllFriends(userId);

		if (actionTypePostgreDAO.getActionTypeByActionTypeId(actionTypeId).getEntity().equals("achievement")) {
			activityPostgreDAO.createActivity(userId, actionTypeId);
		}

		for (User user : users) {
			activityPostgreDAO.createActivity(user.getId(), action.getActionId());
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

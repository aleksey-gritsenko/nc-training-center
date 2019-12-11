package ua.com.nc.nctrainingproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.nc.nctrainingproject.models.Action;
import ua.com.nc.nctrainingproject.models.ActionType;
import ua.com.nc.nctrainingproject.models.Activity;
import ua.com.nc.nctrainingproject.models.User;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.ActionPostgreDAO;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.ActionTypePostgreDAO;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.ActivityPostgreDAO;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.UserPostgreDAO;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActivityService {

	private final ActivityPostgreDAO activityPostgreDAO;
	private final UserPostgreDAO userPostgreDAO;
	private final ActionTypePostgreDAO actionTypePostgreDAO;
	private final ActionPostgreDAO actionPostgreDAO;

	@Autowired
	public ActivityService(ActivityPostgreDAO activityPostgreDAO, UserPostgreDAO userPostgreDAO,
						   ActionTypePostgreDAO actionTypePostgreDAO, ActionPostgreDAO actionPostgreDAO) {
		this.activityPostgreDAO = activityPostgreDAO;
		this.userPostgreDAO = userPostgreDAO;
		this.actionPostgreDAO = actionPostgreDAO;
		this.actionTypePostgreDAO = actionTypePostgreDAO;
	}

	public List<Activity> getAllActivities() {
		return activityPostgreDAO.getAllActivities();
	}

	public Activity createActivity(Activity activity) {
		if (userPostgreDAO.getUserById(activity.getUserId()) == null &&
				activityPostgreDAO.getActivityByActionID(activity.getActionId()) == null) {
			activityPostgreDAO.createActivity(activity.getUserId(), activity.getActionId());
		}
		return activity;
	}

	public Activity deleteActivity(Activity activity) {
		if (userPostgreDAO.getUserById(activity.getUserId()) != null) {
			activityPostgreDAO.deleteActivity(activity.getUserId());
		}
		return activity;
	}

	public List<Activity> getActivityByActionID(int actionId) {
		List<String> data = new ArrayList<>();
		List<Activity> activities = new ArrayList<>();
		if (actionPostgreDAO.getActionById(actionId) != null) {
			activities = activityPostgreDAO.getActivityByActionID(actionId);
		}
		return activities;
	}

	public List<String> getActivityByUserID(int userId) {
		List<String> data = new ArrayList<>();
		List<Action> actions = new ArrayList<>();
		List<ActionType> actionTypes = new ArrayList<>();

		if (userPostgreDAO.getUserById(userId) != null) {
			User user = userPostgreDAO.getUserById(userId);
			List<Activity> activities = activityPostgreDAO.getActivityByUserID(userId);

			for (Activity activity : activities) {
				actions.add(actionPostgreDAO.getActionById(activity.getActionId()));
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

	public List<Activity> getActivityByUserActionID(int userId, int actionId) {
		return activityPostgreDAO.getActivityByUserActionID(userId, actionId);
	}
}

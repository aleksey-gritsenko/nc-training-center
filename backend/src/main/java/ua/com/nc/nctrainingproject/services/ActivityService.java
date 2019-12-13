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
		if (userPostgreDAO.getUserById(activity.getUserId()) == null) {
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

	public List<String> getActivityByUserID(int userId) {
		List<String> data = new ArrayList<>();

		if (userPostgreDAO.getUserById(userId) != null) {
			List<Activity> activities = activityPostgreDAO.getActivityByUserID(userId);

			activities.forEach(activity -> {
				Action action = actionPostgreDAO.getActionById(activity.getActionId());
				User actionUser = userPostgreDAO.getUserById(action.getUserId());
				ActionType actionType = actionTypePostgreDAO.getActionTypeByActionTypeId(action.getActionTypeId());

				if (actionUser.getId() == userId && actionType.getEntity().equals("achievement")) {
					data.add("You " + actionType.getActionName());
				} else {
					data.add(actionUser.getUserName() + " " + actionType.getActionName());
				}
				deleteActivity(activity);
			});
		}
		return data;
	}
}

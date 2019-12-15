package ua.com.nc.nctrainingproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.nc.nctrainingproject.models.*;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActivityService {

    private final ActivityPostgreDAO activityPostgreDAO;
    private final UserPostgreDAO userPostgreDAO;
    private final ActionTypePostgreDAO actionTypePostgreDAO;
    private final ActionPostgreDAO actionPostgreDAO;
    private final UserSettingsPostgreDAO userSettingsPostgreDAO;
    private final UserSettingsService userSettingsService;

    @Autowired
    public ActivityService(ActivityPostgreDAO activityPostgreDAO, UserPostgreDAO userPostgreDAO,
                           ActionTypePostgreDAO actionTypePostgreDAO, ActionPostgreDAO actionPostgreDAO, UserSettingsPostgreDAO userSettingsPostgreDAO, UserSettingsService userSettingsService) {
        this.activityPostgreDAO = activityPostgreDAO;
        this.userPostgreDAO = userPostgreDAO;
        this.actionPostgreDAO = actionPostgreDAO;
        this.actionTypePostgreDAO = actionTypePostgreDAO;
        this.userSettingsPostgreDAO = userSettingsPostgreDAO;
        this.userSettingsService = userSettingsService;
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
        UserSettings userSettings = userSettingsPostgreDAO.getSettingsListById(userId);

        if (userPostgreDAO.getUserById(userId) != null) {
            List<Activity> activities = activityPostgreDAO.getActivityByUserID(userId);

            activities.forEach(activity -> {
                Action action = actionPostgreDAO.getActionById(activity.getActionId());
                User actionUser = userPostgreDAO.getUserById(action.getUserId());
                ActionType actionType = actionTypePostgreDAO.getActionTypeByActionTypeId(action.getActionTypeId());

                if (userSettingsService.toShowActivity(actionType.getActionName(), userSettings)) {
                    if (actionUser.getId() == userId && actionType.getEntity().equals("achievement")) {
                        data.add("You " + actionType.getActionName());
                    } else {
                        data.add(actionUser.getUserName() + " " + actionType.getActionName());
                    }
                }
                deleteActivity(activity);
            });
        }
        return data;
    }
}

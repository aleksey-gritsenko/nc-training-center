package ua.com.nc.nctrainingproject.persistance.dao;

import ua.com.nc.nctrainingproject.models.Activity;

import java.util.List;

public interface ActivityDAO {

	List<Activity> getActivityByUserID(int userId);

	List<Activity> getActivityByActionID(int actionId);

	List<Activity> getActivityByUserActionID(int userId, int actionId);

	List<Activity> getAllActivities();

	void deleteActivity(int userId);

	void createActivity(int userId, int actionId);
}

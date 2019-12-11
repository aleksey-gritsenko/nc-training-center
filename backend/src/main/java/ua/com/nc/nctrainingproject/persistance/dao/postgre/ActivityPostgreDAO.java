package ua.com.nc.nctrainingproject.persistance.dao.postgre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.com.nc.nctrainingproject.models.Activity;
import ua.com.nc.nctrainingproject.persistance.dao.AbstractDAO;
import ua.com.nc.nctrainingproject.persistance.dao.ActivityDAO;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.ActivityQuery;
import ua.com.nc.nctrainingproject.persistance.mappers.ActivityRowMapper;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class ActivityPostgreDAO extends AbstractDAO<Activity> implements ActivityDAO {

	@Autowired
	public ActivityPostgreDAO(DataSource dataSource) {
		super(dataSource);
	}

	@Override
	public List<Activity> getActivityByUserID(int userId) {
		return jdbcTemplate.query(ActivityQuery.GET_BY_USER_ID, new ActivityRowMapper(), userId);
	}

	@Override
	public List<Activity> getActivityByActionID(int actionId) {
		return jdbcTemplate.query(ActivityQuery.GET_BY_ACTION_ID,
				new ActivityRowMapper(), actionId);
	}

	@Override
	public List<Activity> getActivityByUserActionID(int userId, int actionId) {
		return jdbcTemplate.query(ActivityQuery.GET_BY_USER_ACTION_ID,
				new ActivityRowMapper(), actionId, userId);
	}

	@Override
	public List<Activity> getAllActivities() {
		return jdbcTemplate.query(ActivityQuery.GET_ALL_ACTIVITIES, new ActivityRowMapper());
	}

	@Override
	public void deleteActivity(int userId) {
		jdbcTemplate.update(ActivityQuery.DELETE_BY_USER_ID, userId);
	}

	@Override
	public void createActivity(int userId, int actionId) {
		jdbcTemplate.update(ActivityQuery.CREATE_ACTIVITY, actionId, userId);
	}
}

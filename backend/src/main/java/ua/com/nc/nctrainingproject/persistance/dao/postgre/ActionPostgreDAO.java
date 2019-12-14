package ua.com.nc.nctrainingproject.persistance.dao.postgre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.com.nc.nctrainingproject.models.Action;
import ua.com.nc.nctrainingproject.persistance.dao.ActionDAO;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.ActionQuery;
import ua.com.nc.nctrainingproject.persistance.mappers.ActionRowMapper;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class ActionPostgreDAO implements ActionDAO {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public ActionPostgreDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Action> getAllActions() {
		return jdbcTemplate.query(ActionQuery.GET_ALL_ACTIONS, new ActionRowMapper());
	}

	@Override
	public List<Action> getAllActionsByUserIdAndActionTypeId(int userId, int actionTypeId) {
		return jdbcTemplate.query(ActionQuery.GET_ALL_BY_USER_ID_BY_ACTION_TYPE_ID,
				new ActionRowMapper(), userId, actionTypeId);
	}

	@Override
	public Action getActionById(int actionId) {
		List<Action> actionList = jdbcTemplate.query(ActionQuery.GET_BY_ACTION_ID,
				new ActionRowMapper(), actionId);
		if (actionList.size() == 0) {
			return null;
		}
		return actionList.get(0);
	}

	@Override
	public Action getActionByUserId(int userId) {
		return jdbcTemplate.queryForObject(ActionQuery.GET_BY_USER_ID,
				new ActionRowMapper(), userId);
	}

	@Override
	public Action getActionByUserAndTypeId(int userId, int actionTypeId) {
		List<Action> actionList = jdbcTemplate.query(ActionQuery.GET_BY_USER_TYPE_ID,
				new ActionRowMapper(), actionTypeId, userId);
		if (actionList.size() == 0) {
			return null;
		}
		return actionList.get(actionList.size() - 1);
	}

	@Override
	public Action getActionByActionTypeId(int actionTypeId) {
		return jdbcTemplate.queryForObject(ActionQuery.GET_BY_USER_ID,
				new ActionRowMapper(), actionTypeId);
	}

	@Override
	public void deleteActionByActionId(int actionId) {
		jdbcTemplate.update(ActionQuery.DELETE_BY_ACTION_ID, actionId);
	}

	@Override
	public Action createAction(Action action) {
		jdbcTemplate.update(ActionQuery.CREATE_ACTION,
				action.getUserId(), action.getActionTypeId());
		return action;
	}

	@Override
	public void updateAction(int actionId, Action action) {
		jdbcTemplate.update(ActionQuery.UPDATE_ACTION_BY_ID, action.getUserId(),
				action.getActionTypeId(), actionId);
	}
}

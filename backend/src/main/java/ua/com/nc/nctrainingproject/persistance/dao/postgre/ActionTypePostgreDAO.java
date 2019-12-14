package ua.com.nc.nctrainingproject.persistance.dao.postgre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.com.nc.nctrainingproject.models.ActionType;
import ua.com.nc.nctrainingproject.persistance.dao.ActionTypeDAO;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.ActionTypeQuery;
import ua.com.nc.nctrainingproject.persistance.mappers.ActionTypeRowMapper;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class ActionTypePostgreDAO implements ActionTypeDAO {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public ActionTypePostgreDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<ActionType> getAllActionTypes() {
		return jdbcTemplate.query(ActionTypeQuery.GET_ALL_ACTION_TYPES, new ActionTypeRowMapper());
	}

	@Override
	public ActionType getActionTypeByActionTypeId(int actionTypeId) {
		return jdbcTemplate.queryForObject(ActionTypeQuery.GET_BY_ACTION_TYPE_ID,
				new ActionTypeRowMapper(), actionTypeId);
	}

	@Override
	public ActionType getActionTypeByName(String name) {
		return jdbcTemplate.queryForObject(ActionTypeQuery.GET_BY_ACTION_NAME,
				new ActionTypeRowMapper(), name);
	}

	@Override
	public void deleteByActionTypeId(int actionTypeId) {
		jdbcTemplate.update(ActionTypeQuery.DELETE_BY_ACTION_TYPE_ID, actionTypeId);
	}

	@Override
	public void createActionType(ActionType actionType) {
		jdbcTemplate.update(ActionTypeQuery.CREATE_ACTION_TYPE,
				actionType.getActionTypeId(), actionType.getActionName(), actionType.getEntity());
	}

	@Override
	public void updateActionTypeById(int actionTypeId, ActionType actionType) {
		jdbcTemplate.update(ActionTypeQuery.UPDATE_ACTION_TYPE_BY_ID,
				actionType.getActionName(), actionTypeId);
	}
}

package ua.com.nc.nctrainingproject.persistance.mappers;

import org.springframework.jdbc.core.RowMapper;
import ua.com.nc.nctrainingproject.models.Action;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.ActionQuery;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ActionRowMapper implements RowMapper<Action> {
	@Override
	public Action mapRow(ResultSet resultSet, int i) throws SQLException {
		Action action = new Action();
		action.setActionId(resultSet.getInt(ActionQuery.ACTION_ID));
		action.setUserId(resultSet.getInt(ActionQuery.USER_ID));
		action.setActionTypeId(resultSet.getInt(ActionQuery.ACTION_TYPE_ID));

		return action;
	}
}

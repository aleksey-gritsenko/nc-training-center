package ua.com.nc.nctrainingproject.persistance.mappers;

import org.springframework.jdbc.core.RowMapper;
import ua.com.nc.nctrainingproject.models.ActionType;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.ActionTypeQuery;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ActionTypeRowMapper implements RowMapper<ActionType> {
	@Override
	public ActionType mapRow(ResultSet resultSet, int i) throws SQLException {
		ActionType actionType = new ActionType();
		actionType.setActionName(resultSet.getString(ActionTypeQuery.ACTION_NAME));
		actionType.setActionName(resultSet.getString(ActionTypeQuery.ACTION_TYPE_ID));

		return actionType;
	}
}

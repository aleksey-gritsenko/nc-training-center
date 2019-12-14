package ua.com.nc.nctrainingproject.persistance.dao.postgre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.RightsQuery;

import java.util.List;

@Repository
public class RightsPostgreDAO {
	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public RightsPostgreDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public String getDescriptionByRightId(int rightId) {

		return jdbcTemplate.queryForObject(RightsQuery.GET_DESCRIPTION_BY_RIGHT_ID, new Object[]{rightId}, String.class);
	}

	public Integer getRightIdByDescription(String description) {

		return jdbcTemplate.queryForObject(RightsQuery.GET_RIGHT_ID_BY_DESCRIPTION, new Object[]{description}, Integer.class);
	}

	public List<String> getAllDescriptions() {
		return jdbcTemplate.queryForList(RightsQuery.GET_ALL_DESCRIPTIONS, String.class);
	}
}

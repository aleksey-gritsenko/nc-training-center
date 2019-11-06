package ua.com.nc.nctrainingproject.persistance.dao.postgre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.com.nc.nctrainingproject.models.Admin;
import ua.com.nc.nctrainingproject.persistance.dao.AdministratorDAO;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.AdminQuery;
import ua.com.nc.nctrainingproject.persistance.mappers.AdminRowMapper;


@Repository
public class AdministratorPostgreDAO implements AdministratorDAO {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public AdministratorPostgreDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Admin getAdministratorByName(String name) {
		try {
			return jdbcTemplate.queryForObject(AdminQuery.GET_ADMIN_BY_NAME, new Object[]{name}, new AdminRowMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public void updateAdminPassword(String password, String agminName) {
		jdbcTemplate.update(AdminQuery.UPDATE_PASSWORD, password, agminName);
	}

	@Override
	public void createAdministrator(Admin admin) {
		jdbcTemplate.update(AdminQuery.CREATE_ADMIN, admin.getAdminName(), admin.getAdminPassword(), admin.getEmail(), "admin");
	}
}

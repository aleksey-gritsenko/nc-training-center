package ua.com.nc.nctrainingproject.persistance.dao.postgre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;
import ua.com.nc.nctrainingproject.models.User;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.AdminRightsQuery;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AdminRightsPostgreDAO {

	private final JdbcTemplate jdbcTemplate;
	private final UserPostgreDAO userPostgreDAO;
	private final RightsPostgreDAO rightsPostgreDAO;

	@Autowired
	public AdminRightsPostgreDAO(DataSource dataSource, UserPostgreDAO userPostgreDAO, RightsPostgreDAO rightsPostgreDAO) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.userPostgreDAO = userPostgreDAO;
		this.rightsPostgreDAO = rightsPostgreDAO;
	}


	public List<User> getUsersForRightId(int id) {
		List<Integer> userIds = jdbcTemplate.queryForList(AdminRightsQuery.GET_ADMIN_ID_BY_RIGHT_ID, Integer.class, id);
		List<User> users = new ArrayList<>();
		for (Integer adminId : userIds) {
			users.add(userPostgreDAO.getUserById(adminId));
		}
		return users;
	}

	public List<Integer> getRightsIdByUserId(int id) {
		return jdbcTemplate.queryForList(AdminRightsQuery.GET_RIGHT_ID_BY_ADMIN_ID, Integer.class, id);
	}

	public void deleteRightsByUserId(int id) {
		jdbcTemplate.update(AdminRightsQuery.DELETE_BY_ADMIN_ID, id);
	}

	public void add(int adminId, int rightId) {
		jdbcTemplate.update(AdminRightsQuery.CREATE_PAIR, adminId, rightId);

	}

	public List<SimpleGrantedAuthority> getAllAuthoritiesByAdminId(int adminId) {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();

		for (Integer i : getRightsIdByUserId(adminId)) {
			authorities.add(new SimpleGrantedAuthority(rightsPostgreDAO.getDescriptionByRightId(i)));
		}
		return authorities;
	}

}

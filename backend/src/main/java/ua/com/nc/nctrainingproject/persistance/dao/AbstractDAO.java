package ua.com.nc.nctrainingproject.persistance.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ua.com.nc.nctrainingproject.models.Entity;

import javax.sql.DataSource;
import java.util.List;

@Repository
public abstract class AbstractDAO<T extends Entity> {

	protected final JdbcTemplate jdbcTemplate;

	@Autowired
	public AbstractDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	protected void create(String query, Object[] params) {
		jdbcTemplate.update(query, params);
	}

	protected void update(String query, Object[] params) {
		jdbcTemplate.update(query, params);
	}

	public T getEntityById(String query, RowMapper<T> rowMapper, int id) {
		List<T> entities = jdbcTemplate.query(query, rowMapper, id);
		if (entities.size() == 0) {
			return null;
		}
		return entities.get(0);
	}

	public List<T> getAllEntities(String query, RowMapper<T> rowMapper) {
		return jdbcTemplate.query(query, rowMapper);
	}

	public void deleteEntityById(String query, int id) {
		jdbcTemplate.update(query, id);
	}

	public void updateEntityById(int id, Object[] params, String sql) {
		jdbcTemplate.update(sql, params);
	}
}

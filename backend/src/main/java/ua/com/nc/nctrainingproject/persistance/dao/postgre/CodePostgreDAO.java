package ua.com.nc.nctrainingproject.persistance.dao.postgre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.com.nc.nctrainingproject.models.RecoverCode;
import ua.com.nc.nctrainingproject.persistance.dao.CodeRecoverDAO;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.CodeRecoverQuery;
import ua.com.nc.nctrainingproject.persistance.mappers.CodeRowMapper;

import javax.sql.DataSource;
import java.util.Date;

@Repository
public class CodePostgreDAO implements CodeRecoverDAO {
	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public CodePostgreDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void createCode(String code, String email) {
		jdbcTemplate.update(CodeRecoverQuery.CREATE_CODE, code, new Date(), email);
	}

	public RecoverCode getCodeBy(String code) {
		try {
			return jdbcTemplate.queryForObject(CodeRecoverQuery.GET_CODE, new Object[]{code}, new CodeRowMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public void deleteByCode(String code) {
		jdbcTemplate.update(CodeRecoverQuery.DELETE_CODE, code);
	}
	public void deleteByCodeEmail(String email){jdbcTemplate.update(CodeRecoverQuery.DELETE_CODE_EMAIL, email);}
}

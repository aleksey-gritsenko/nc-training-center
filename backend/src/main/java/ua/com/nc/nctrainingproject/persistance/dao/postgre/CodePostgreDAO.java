package ua.com.nc.nctrainingproject.persistance.dao.postgre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.com.nc.nctrainingproject.models.RecoverCode;
import ua.com.nc.nctrainingproject.persistance.dao.CodeRecoverDAO;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.CodeRecoverQuery;
import ua.com.nc.nctrainingproject.persistance.mappers.CodeRowMapper;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class CodePostgreDAO implements CodeRecoverDAO {
	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public CodePostgreDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void createCode(String code, String email) {
		jdbcTemplate.update(CodeRecoverQuery.CREATE_CODE, code,
				LocalDateTime.now(), email);
		System.out.println(LocalDateTime.now());
	}

	public RecoverCode getCodeBy(String code) {
		List<RecoverCode> codeList = jdbcTemplate.query(CodeRecoverQuery.GET_CODE, new CodeRowMapper(), code);
		if (codeList.size() == 0) {
			return null;
		}
		return codeList.get(0);


	}

	public RecoverCode getCodeByEmail(String email) {

		List<RecoverCode> codeList = jdbcTemplate.query(CodeRecoverQuery.GET_CODE_BY_EMAIL, new CodeRowMapper(), email);
		if (codeList.size() == 0) {
			return null;
		}
		return codeList.get(0);

	}

	public void deleteAll() {
		jdbcTemplate.update(CodeRecoverQuery.DELETE_ALL_HOUR);
	}

	public void deleteByCode(String code) {
		jdbcTemplate.update(CodeRecoverQuery.DELETE_CODE, code);
	}

	public void deleteByCodeEmail(String email) {
		jdbcTemplate.update(CodeRecoverQuery.DELETE_CODE_EMAIL, email);
	}

	public Integer checkDB() {
		return jdbcTemplate.queryForObject(
				CodeRecoverQuery.CHECK_DB, Integer.class);
	}
}

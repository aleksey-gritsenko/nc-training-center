package ua.com.nc.nctrainingproject.persistance.dao.postgre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.GenreQuery;

@Repository
public class GenrePostgreDAO {
	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public GenrePostgreDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public String getGenreById(int id) {
		try {
			return jdbcTemplate.queryForObject(GenreQuery.GET_GENRE_BY_ID, String.class, id);
		} catch (IncorrectResultSizeDataAccessException e) {
			return null;
		}
	}

	public Integer getIdByGenre(String genre) {
		try {
			return jdbcTemplate.queryForObject(GenreQuery.GET_ID_BY_GENRE, Integer.class, genre);
		} catch (IncorrectResultSizeDataAccessException e) {
			return null;
		}
	}
	public void createGenre(String genreName) {
		jdbcTemplate.update(GenreQuery.CREATE_GENRE, genreName);
	}


	//public Integer getGenreByBook
}

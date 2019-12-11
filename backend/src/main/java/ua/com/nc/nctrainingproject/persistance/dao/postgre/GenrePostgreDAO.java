package ua.com.nc.nctrainingproject.persistance.dao.postgre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.com.nc.nctrainingproject.models.Genre;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.GenreQuery;
import ua.com.nc.nctrainingproject.persistance.mappers.GenreMapper;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class GenrePostgreDAO {
	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public GenrePostgreDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
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
			//TODO rework for situation when there 2 simular genres
			createGenre(genre);
			return getIdByGenre(genre);
		}
	}

	public void createGenre(String genreName) {
		jdbcTemplate.update(GenreQuery.CREATE_GENRE, genreName);
	}

	public List<Genre> getAllGenres() {
		try {
			return jdbcTemplate.query(GenreQuery.GET_ALL_GENRES, new GenreMapper());
		} catch (IncorrectResultSizeDataAccessException e) {
			return null;
		}
	}

	public List<String> getAllGenresName() {
		try {
			return jdbcTemplate.queryForList(GenreQuery.GET_ALL_GENRES_NAME, String.class);
		} catch (IncorrectResultSizeDataAccessException e) {
			return null;
		}
	}
	//public Integer getGenreByBook
}

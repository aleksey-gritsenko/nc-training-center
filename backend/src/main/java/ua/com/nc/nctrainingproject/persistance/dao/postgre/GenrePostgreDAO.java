package ua.com.nc.nctrainingproject.persistance.dao.postgre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.com.nc.nctrainingproject.models.Genre;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.GenreQuery;
import ua.com.nc.nctrainingproject.persistance.mappers.GenreMapper;

import java.util.List;

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

	public List<Genre> getAllGenres(){
		try {
			return jdbcTemplate.query(GenreQuery.GET_ALL_GENRES, new GenreMapper());
		}
		catch(IncorrectResultSizeDataAccessException e){
			return null;
		}
	}
	
	//public Integer getGenreByBook
}

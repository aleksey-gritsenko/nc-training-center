package ua.com.nc.nctrainingproject.persistance.dao.postgre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.com.nc.nctrainingproject.models.Achievement;
import ua.com.nc.nctrainingproject.models.AchivementDto;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.AchivementQuery;
import ua.com.nc.nctrainingproject.persistance.mappers.AchievementRowMapper;
import ua.com.nc.nctrainingproject.persistance.mappers.AchivementDtoRowMapper;

import javax.sql.DataSource;
import java.util.List;

@Repository

public class AchivementPostgreDAO {
	private final JdbcTemplate jdbcTemplate;
	private final GenrePostgreDAO genrePostgreDAO;
	private final ActionTypePostgreDAO actionTypePostgreDAO;

	@Autowired
	public AchivementPostgreDAO(DataSource dataSource, GenrePostgreDAO genrePostgreDAO, ActionTypePostgreDAO actionTypePostgreDAO) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.actionTypePostgreDAO = actionTypePostgreDAO;
		this.genrePostgreDAO = genrePostgreDAO;


	}

	public void createAchievement(String achievementName, int action, int genre, int count, String entity) {
		//int genre_id = genrePostgreDAO.getIdByGenre(genre);
		//int action_id = actionTypePostgreDAO.getActionTypeByNameGenre(action, genre_id).getActionTypeId();

		jdbcTemplate.update(AchivementQuery.CREATE_ACHIEVEMENT, achievementName, action, genre, count, entity);
	}

	public void createPair(int userId, int achievementId) {
		jdbcTemplate.update(AchivementQuery.CREATE_PAIR, userId, achievementId);
	}

	public List<AchivementDto> getAllAchievementDto() {
		return jdbcTemplate.query(AchivementQuery.GET_ALL_ACHIEVEMENT_DTO, new AchivementDtoRowMapper());
	}

	public List<Integer> getAllAchievementsByUserId(int id) {
		return jdbcTemplate.queryForList(AchivementQuery.GET_ALL_ACHIEVEMENT_ID_BY_USER_ID, Integer.class, id);
	}

	public AchivementDto getAchievementById(int id) {
		return jdbcTemplate.queryForObject(AchivementQuery.GET_ACHIEVEMENT_DTO_BY_ID, AchivementDto.class, id);
	}

	public List<Achievement> getAllAchievements() {
		return jdbcTemplate.query(AchivementQuery.GET_ALL, new AchievementRowMapper());
	}
}

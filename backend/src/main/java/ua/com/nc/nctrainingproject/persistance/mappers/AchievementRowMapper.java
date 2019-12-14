package ua.com.nc.nctrainingproject.persistance.mappers;

import org.springframework.jdbc.core.RowMapper;
import ua.com.nc.nctrainingproject.models.Achievement;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.AchivementQuery;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AchievementRowMapper implements RowMapper<Achievement> {
	@Override
	public Achievement mapRow(ResultSet resultSet, int i) throws SQLException {
		Achievement achievement = new Achievement();
		achievement.setId(resultSet.getInt(AchivementQuery.ACHIEVEMENT_ID));
		achievement.setAchievementName(resultSet.getString(AchivementQuery.ACHIEVEMENT_NAME));
		achievement.setActionTypeId(resultSet.getInt(AchivementQuery.ACTION));
		achievement.setCount(resultSet.getInt(AchivementQuery.COUNT));
		achievement.setEntity(resultSet.getString(AchivementQuery.ENTITY));
		achievement.setGenreId(resultSet.getInt(AchivementQuery.GENRE_ID));
		return achievement;
	}
}

package ua.com.nc.nctrainingproject.persistance.mappers;

import org.springframework.jdbc.core.RowMapper;
import ua.com.nc.nctrainingproject.models.AchivementDto;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.AchivementQuery;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.ActionTypeQuery;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.GenreQuery;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AchivementDtoRowMapper implements RowMapper<AchivementDto> {
	@Override
	public AchivementDto mapRow(ResultSet resultSet, int i) throws SQLException {
		AchivementDto achivementDto = new AchivementDto();
		achivementDto.setId(resultSet.getInt(AchivementQuery.ACHIEVEMENT_ID));
		achivementDto.setAchievementName(AchivementQuery.ACHIEVEMENT_NAME);
		achivementDto.setAction(resultSet.getString(ActionTypeQuery.ACTION_NAME));
		achivementDto.setCount(resultSet.getInt(AchivementQuery.COUNT));
		achivementDto.setEntity(resultSet.getString(AchivementQuery.ENTITY));
		achivementDto.setGenre(GenreQuery.GENRE_NAME);
		return achivementDto;
	}
}

package ua.com.nc.nctrainingproject.persistance.mappers;

import org.springframework.jdbc.core.RowMapper;
import ua.com.nc.nctrainingproject.models.Achivement;
import ua.com.nc.nctrainingproject.models.Action;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.AchivementQuery;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.ActionQuery;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.ActionTypeQuery;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.GenreQuery;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AchivementRowMapper implements RowMapper<Achivement> {
    @Override
    public Achivement mapRow(ResultSet resultSet, int i) throws SQLException {
        Achivement achivement = new Achivement();
        achivement.setId(resultSet.getInt(AchivementQuery.ACHIEVEMENT_ID));
        achivement.setAchievementName(AchivementQuery.ACHIEVEMENT_NAME);
        achivement.setAction(resultSet.getString(ActionTypeQuery.ACTION_NAME));
        achivement.setCount(resultSet.getInt(AchivementQuery.COUNT));
        achivement.setEntity(resultSet.getString(AchivementQuery.ENTITY));
        achivement.setGenre(GenreQuery.GENRE_NAME);
        return achivement;
    }
}

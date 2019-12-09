package ua.com.nc.nctrainingproject.persistance.dao.postgre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.com.nc.nctrainingproject.models.AchivementDto;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.AchivementQuery;
import ua.com.nc.nctrainingproject.persistance.mappers.AchivementRowMapper;

import javax.sql.DataSource;
import java.util.List;

@Repository

public class AchivementPostgreDAO {
    private final JdbcTemplate jdbcTemplate;
    private final GenrePostgreDAO genrePostgreDAO;
    private final ActionTypePostgreDAO actionTypePostgreDAO;

    @Autowired
    public AchivementPostgreDAO(DataSource dataSource,GenrePostgreDAO genrePostgreDAO,ActionTypePostgreDAO actionTypePostgreDAO) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.actionTypePostgreDAO =actionTypePostgreDAO;
        this.genrePostgreDAO =genrePostgreDAO;


    }
    public void createAchievement(String achievementName,String action,String genre,int count,String entity){
        int genre_id=genrePostgreDAO.getIdByGenre(genre);
        int action_id = actionTypePostgreDAO.getActionTypeByName(action).getActionTypeId();

        jdbcTemplate.update(AchivementQuery.CREATE_ACHIEVEMENT,achievementName,action_id,genre_id,count,entity);
    }
    public List<AchivementDto> getAllAchievements(){
        return jdbcTemplate.query(AchivementQuery.GET_ALL_ACHIEVEMENT_DTO,new AchivementRowMapper());
    }

    public List<Integer> getAllAchievementsByUserId(int id){
        return jdbcTemplate.queryForList(AchivementQuery.GET_ALL_ACHIEVEMENT_ID_BY_USER_ID, Integer.class, id);
    }

    public AchivementDto getAchievementById(int id){
        return jdbcTemplate.queryForObject(AchivementQuery.GET_ACHIEVEMENT_DTO_BY_ID, AchivementDto.class, id);
    }
}

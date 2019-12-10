package ua.com.nc.nctrainingproject.persistance.dao.postgre.queries;

import org.springframework.beans.factory.annotation.Autowired;
import ua.com.nc.nctrainingproject.models.AchivementDto;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.AchivementPostgreDAO;

import java.util.List;

public class AchievementQueryBuilder {
    private List<AchivementDto> achivementDtos;
    private final AchivementPostgreDAO achivementPostgreDAO;

    @Autowired
    public AchievementQueryBuilder(AchivementPostgreDAO achivementPostgreDAO) {
        this.achivementPostgreDAO = achivementPostgreDAO;
    }
    private void getAllAchievements(){
        achivementDtos = achivementPostgreDAO.getAllAchievementDto();
    }

}

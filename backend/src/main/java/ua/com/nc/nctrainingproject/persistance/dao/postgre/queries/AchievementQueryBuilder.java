package ua.com.nc.nctrainingproject.persistance.dao.postgre.queries;

import org.springframework.beans.factory.annotation.Autowired;
import ua.com.nc.nctrainingproject.models.Achivement;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.AchivementPostgreDAO;

import java.util.List;

public class AchievementQueryBuilder {
    private List<Achivement> achivements;
    private final AchivementPostgreDAO achivementPostgreDAO;

    @Autowired
    public AchievementQueryBuilder(AchivementPostgreDAO achivementPostgreDAO) {
        this.achivementPostgreDAO = achivementPostgreDAO;
    }
    private void getAllAchievements(){
        achivements = achivementPostgreDAO.getAllAchievements();
    }

}

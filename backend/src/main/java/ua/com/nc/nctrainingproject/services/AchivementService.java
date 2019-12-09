package ua.com.nc.nctrainingproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.nc.nctrainingproject.models.Achievement;
import ua.com.nc.nctrainingproject.models.AchivementDto;
import ua.com.nc.nctrainingproject.models.User;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.AchivementPostgreDAO;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.ActionPostgreDAO;

import java.util.ArrayList;
import java.util.List;

@Service
public class AchivementService {
    private final AchivementPostgreDAO achivementPostgreDAO;
    private final ActionPostgreDAO actionPostgreDAO;



    @Autowired
    public AchivementService(AchivementPostgreDAO achivementPostgreDAO, ActionPostgreDAO actionPostgreDAO) {
        this.achivementPostgreDAO = achivementPostgreDAO;
        this.actionPostgreDAO = actionPostgreDAO;
    }
    public void createAchevementDto(AchivementDto achivementDto){
       achivementPostgreDAO.createAchievement(achivementDto.getAchievementName(),achivementDto.getAction(),achivementDto.getGenre(),achivementDto.getCount()
               ,achivementDto.getEntity());
    }
    public List<AchivementDto> getAllAchievementDtos(){
        return achivementPostgreDAO.getAllAchievementDto();
    }
    private List<AchivementDto> achivementDtos;

    public List<AchivementDto> getAllAchievementDtosForUser(int userId){
        List<Integer> achievementIds = achivementPostgreDAO.getAllAchievementsByUserId(userId);
        List<AchivementDto> result = new ArrayList<>();
        for (Integer i : achievementIds){
            result.add(achivementPostgreDAO.getAchievementById(i.intValue()));

        }
        return result;
    }

    //TODO make this method Sheduled
    public void assignAchievements(){
        //TODO get cur user from session
        User currentUser = new User();

        List<Achievement> achievements = achivementPostgreDAO.getAllAchievements();

        for (Achievement achievement: achievements){
            if (actionPostgreDAO.getAllActionsByUserIdAndActionTypeId(currentUser.getId(),achievement.getActionTypeId())
                    .size() >= achievement.getCount() && !(achivementPostgreDAO.getAllAchievementsByUserId(currentUser.getId()).contains((achievement.getId())))){
                achivementPostgreDAO.createPair(currentUser.getId(),achievement.getGenreId());
            }

        }

    }

}

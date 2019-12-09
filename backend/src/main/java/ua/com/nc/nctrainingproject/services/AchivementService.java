package ua.com.nc.nctrainingproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.nc.nctrainingproject.models.AchivementDto;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.AchivementPostgreDAO;

import java.util.ArrayList;
import java.util.List;

@Service
public class AchivementService {
    private final AchivementPostgreDAO achivementPostgreDAO;

    @Autowired
    public AchivementService(AchivementPostgreDAO achivementPostgreDAO) {
        this.achivementPostgreDAO = achivementPostgreDAO;
    }
    public void createAchevementDto(AchivementDto achivementDto){
       achivementPostgreDAO.createAchievement(achivementDto.getAchievementName(),achivementDto.getAction(),achivementDto.getGenre(),achivementDto.getCount()
               ,achivementDto.getEntity());
    }
    public List<AchivementDto> getAllAchievementDtos(){
        return achivementPostgreDAO.getAllAchievements();
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
}

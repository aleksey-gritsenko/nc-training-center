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
	private List<AchivementDto> achivementDtos;

	@Autowired
	public AchivementService(AchivementPostgreDAO achivementPostgreDAO, ActionPostgreDAO actionPostgreDAO) {
		this.achivementPostgreDAO = achivementPostgreDAO;
		this.actionPostgreDAO = actionPostgreDAO;
	}

	public void createAchevement(Achievement achivement) {
		achivementPostgreDAO.createAchievement(achivement.getAchievementName(), achivement.getActionTypeId(), achivement.getGenreId(), achivement.getCount()
				, achivement.getEntity());
	}

	public List<AchivementDto> getAllAchievementDtos() {
		return achivementPostgreDAO.getAllAchievementDto();
	}

	public List<AchivementDto> getAllAchievementDtosForUser(int userId) {
		List<Integer> achievementIds = achivementPostgreDAO.getAllAchievementsByUserId(userId);
		List<AchivementDto> result = new ArrayList<>();
		for (Integer i : achievementIds) {
			result.add(achivementPostgreDAO.getAchievementById(i.intValue()));

		}
		return result;
	}


	public void assignAchievements(int id) {


		List<Achievement> achievements = achivementPostgreDAO.getAllAchievements();

		for (Achievement achievement : achievements) {
			if (actionPostgreDAO.getAllActionsByUserIdAndActionTypeId(id, achievement.getActionTypeId())
					.size() >= achievement.getCount() && !(achivementPostgreDAO.getAllAchievementsByUserId(id).contains((achievement.getId())))) {
				achivementPostgreDAO.createPair(id, achievement.getId());
			}

		}

	}

}

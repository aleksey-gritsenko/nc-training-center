package ua.com.nc.nctrainingproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.nc.nctrainingproject.models.Achievement;
import ua.com.nc.nctrainingproject.models.AchivementDto;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.AchivementPostgreDAO;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.ActionPostgreDAO;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.ActionTypePostgreDAO;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.GenrePostgreDAO;

import java.util.ArrayList;
import java.util.List;

@Service
public class AchivementService {
	private final AchivementPostgreDAO achivementPostgreDAO;
	private final ActionPostgreDAO actionPostgreDAO;
	private final ActionTypePostgreDAO actionTypePostgreDAO;
	private final GenrePostgreDAO genrePostgreDAO;
	private List<AchivementDto> achivementDtos;

	@Autowired
	public AchivementService(AchivementPostgreDAO achivementPostgreDAO, ActionPostgreDAO actionPostgreDAO, ActionTypePostgreDAO actionTypePostgreDAO, GenrePostgreDAO genrePostgreDAO) {
		this.achivementPostgreDAO = achivementPostgreDAO;
		this.actionPostgreDAO = actionPostgreDAO;
		this.actionTypePostgreDAO = actionTypePostgreDAO;
		this.genrePostgreDAO = genrePostgreDAO;
	}

	public void createAchevement(Achievement achivement) {
		achivementPostgreDAO.createAchievement(achivement.getAchievementName(), achivement.getActionTypeId(), achivement.getGenreId(), achivement.getCount()
				, achivement.getEntity());
	}

	public List<AchivementDto> getAllAchievementDtos() {
		return achivementPostgreDAO.getAllAchievementDto();
	}

	public List<AchivementDto> getAllAchievementDtosForUser(int userId) {
		List<Integer> achievementIds = achivementPostgreDAO.getAllAchievementIdsByUserId(userId);
		List<AchivementDto> result = new ArrayList<>();
		for (Integer i : achievementIds) {
			result.add(achievementToDto(achivementPostgreDAO.getAchievementById(i.intValue())));

		}
		return result;
	}


	private AchivementDto achievementToDto(Achievement achievement){
		AchivementDto result = new AchivementDto();
		result.setAchievementName(achievement.getAchievementName());
		result.setAction(actionTypePostgreDAO.getActionTypeByActionTypeId(achievement.getActionTypeId()).getActionName());
		result.setCount(achievement.getCount());
		result.setEntity(achievement.getEntity());
		result.setId(achievement.getId());
		if(result.getEntity() == "book"){
			result.setGenre(genrePostgreDAO.getGenreById(achievement.getGenreId()));
		}
		return result;
	}

	public void assignAchievements(int id) {


		List<Achievement> achievements = achivementPostgreDAO.getAllAchievements();

		for (Achievement achievement : achievements) {
			if (actionPostgreDAO.getAllActionsByUserIdAndActionTypeId(id, achievement.getActionTypeId())
					.size() >= achievement.getCount() && !(achivementPostgreDAO.getAllAchievementIdsByUserId(id).contains((achievement.getId())))) {
				achivementPostgreDAO.createPair(id, achievement.getId());
			}

		}

	}

}

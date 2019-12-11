package ua.com.nc.nctrainingproject.models;

public class Achievement extends Entity {


	private String achievementName;
	private int actionTypeId;
	private int genreId;
	private int count;
	private String entity;

	public Achievement() {
	}

	public Achievement(String achievementName, int actionTypeId, int genreId, int count, String entity) {
		this.achievementName = achievementName;
		this.actionTypeId = actionTypeId;
		this.genreId = genreId;
		this.count = count;
		this.entity = entity;
	}

	public String getAchievementName() {
		return achievementName;
	}

	public void setAchievementName(String achievementName) {
		this.achievementName = achievementName;
	}

	public int getActionTypeId() {
		return actionTypeId;
	}

	public void setActionTypeId(int actionTypeId) {
		this.actionTypeId = actionTypeId;
	}

	public int getGenreId() {
		return genreId;
	}

	public void setGenreId(int genreId) {
		this.genreId = genreId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}
}

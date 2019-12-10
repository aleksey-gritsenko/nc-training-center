package ua.com.nc.nctrainingproject.models;

public class AchivementDto extends Entity {

    private String achievementName;
    private String action;
    private String genre;
    private int count;
    private String entity;


    public String getAchievementName() {
        return achievementName;
    }

    public void setAchievementName(String achievementName) {
        this.achievementName = achievementName;
    }


    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
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

    public AchivementDto() {
    }

    public AchivementDto(String achievementName, String action, String genre, int count, String entity) {
        this.achievementName = achievementName;
        this.action = action;
        this.genre = genre;
        this.count = count;
        this.entity = entity;
    }
}

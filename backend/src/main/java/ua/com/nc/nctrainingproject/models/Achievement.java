package ua.com.nc.nctrainingproject.models;

public class Achievement extends Entity {

    private String achievementName;

    public String getAchievementName() {
        return achievementName;
    }

    public void setAchievementName(String achievementName) {
        this.achievementName = achievementName;
    }

    private int action_type_id;
    private int genre_id;
    private int count;
    private String entity;

    public int getAction_type_id() {
        return action_type_id;
    }

    public void setAction_type_id(int action_type_id) {
        this.action_type_id = action_type_id;
    }

    public int getGenre_id() {
        return genre_id;
    }

    public void setGenre_id(int genre_id) {
        this.genre_id = genre_id;
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

    public Achievement() {
    }

    public Achievement(String achievementName, int action_type_id, int genre_id, int count, String entity) {
        this.achievementName = achievementName;
        this.action_type_id = action_type_id;
        this.genre_id = genre_id;
        this.count = count;
        this.entity = entity;
    }
}

package ua.com.nc.nctrainingproject.persistance.dao.postgre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.com.nc.nctrainingproject.models.UserSettings;
import ua.com.nc.nctrainingproject.persistance.dao.SettingsListDAO;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.SettingsListQuery;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.UserSettingsQuery;
import ua.com.nc.nctrainingproject.persistance.mappers.UserSettingsRowMaper;

import javax.sql.DataSource;

@Repository
public class SettingsListPostgreDAO implements SettingsListDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SettingsListPostgreDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public UserSettings getUserSettingsById(int settingsId) {
        return jdbcTemplate.queryForObject(SettingsListQuery.GET_USER_SETTINGS, new UserSettingsRowMaper(), settingsId);
    }

    public void updateSettings(int settingsId, UserSettings userSettings) {
        jdbcTemplate.update(SettingsListQuery.UPDATE_USER_SETTINGS,userSettings.getSubscribeOnFriends(),userSettings.getAchievements(),
                userSettings.getBookNotification(),userSettings.getSubscribeOnFriendReview(),userSettings.getNotifyAboutNewFriends(),userSettings.getNotifyAboutAchievement(),settingsId);
    }
}

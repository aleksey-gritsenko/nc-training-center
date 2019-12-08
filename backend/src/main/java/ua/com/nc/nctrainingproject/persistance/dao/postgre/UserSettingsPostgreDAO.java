package ua.com.nc.nctrainingproject.persistance.dao.postgre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.com.nc.nctrainingproject.models.UserSettings;
import ua.com.nc.nctrainingproject.persistance.dao.UserSettingsDAO;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.UserSettingsQuery;

import javax.sql.DataSource;

@Repository
public class UserSettingsPostgreDAO implements UserSettingsDAO {
    private final JdbcTemplate jdbcTemplate;
    private final SettingsListPostgreDAO settingsListPostgreDAO;


    @Autowired
    public UserSettingsPostgreDAO(DataSource dataSource, SettingsListPostgreDAO settingsListPostgreDAO) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.settingsListPostgreDAO = settingsListPostgreDAO;
    }


    @Override
    public int getSettingsListId(int userId) {
        return jdbcTemplate.queryForObject(UserSettingsQuery.GET_SETTINGS_LIST_ID, Integer.class, userId);
    }

    public UserSettings getSettingsListById(int userId) {
        return settingsListPostgreDAO.getUserSettingsById(getSettingsListId(userId));
    }
}

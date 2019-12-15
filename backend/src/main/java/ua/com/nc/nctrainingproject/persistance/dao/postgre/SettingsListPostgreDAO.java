package ua.com.nc.nctrainingproject.persistance.dao.postgre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ua.com.nc.nctrainingproject.models.UserSettings;
import ua.com.nc.nctrainingproject.persistance.dao.SettingsListDAO;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.SettingsListQuery;
import ua.com.nc.nctrainingproject.persistance.mappers.UserSettingsRowMaper;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

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
        jdbcTemplate.update(SettingsListQuery.UPDATE_USER_SETTINGS, userSettings.getNotifyAboutAnnouncements(), userSettings.getAchievements(),
                userSettings.getBookNotification(), userSettings.getSubscribeOnFriendReview(), userSettings.getNotifyAboutNewFriends(), userSettings.getNotifyAboutAchievement(), settingsId);
    }

    public void createSettings(int userId) {
        jdbcTemplate.update(SettingsListQuery.CREATE_SETTINGS,userId);
    }

    public int getSettingsListIdByUserId(int userId) {
        return jdbcTemplate.queryForObject(SettingsListQuery.GET_ID, new Object[]{userId}, Integer.class);
    }



    public void insertMessage() {
      //  System.out.println(getSettingsListIdByUserId(111));
        //  createSettings();
        //  createSettings();
//        int key =0;
//        try {
//          Connection connection= dataSource.getConnection();
//            PreparedStatement preparedStatement = connection.prepareStatement(SettingsListQuery.CREATE_SETTINGS, Statement.RETURN_GENERATED_KEYS);
//            preparedStatement.executeUpdate();
//            ResultSet keys = preparedStatement.getGeneratedKeys();
//            keys.next();
//            key = keys.getInt(1);
//            System.out.println(key);
//            keys.close();
//            preparedStatement.close();
//            connection.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }


//        KeyHolder keyHolder = new GeneratedKeyHolder();
//
//        jdbcTemplate.update(connection -> {
//            PreparedStatement ps = connection
//                    .prepareStatement(SettingsListQuery.CREATE_SETTINGS);
//            return ps;
//        }, keyHolder);
//
//        System.out.println(keyHolder.getKey());


//        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
//        jdbcInsert.withTableName(SettingsListQuery.TABLE_NAME).usingGeneratedKeyColumns(
//                "settings_list_id");
//        Map<String, Object> parameters = new HashMap<>();
//        parameters.put("subscribe_on_friends", true);
//        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(
//                parameters));
//        System.out.println(((Number) key).intValue());


    }
}

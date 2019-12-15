package ua.com.nc.nctrainingproject.persistance.mappers;

import org.springframework.jdbc.core.RowMapper;
import ua.com.nc.nctrainingproject.models.UserSettings;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.SettingsListQuery;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserSettingsRowMaper implements RowMapper<UserSettings> {
	@Override
	public UserSettings mapRow(ResultSet resultSet, int i) throws SQLException {
		UserSettings userSettings = new UserSettings();
		userSettings.setNotifyAboutAnnouncements(resultSet.getBoolean(SettingsListQuery.SUBSCRIBE_ON_FRIENDS));
		userSettings.setAchievements(resultSet.getBoolean(SettingsListQuery.ACHIEVEMENTS));
		userSettings.setBookNotification(resultSet.getBoolean(SettingsListQuery.BOOK_NOTIFICATION));
		userSettings.setSubscribeOnFriendReview(resultSet.getBoolean(SettingsListQuery.SUBSCRIBE_ON_FRIEND_REVIEW));
		userSettings.setNotifyAboutNewFriends(resultSet.getBoolean(SettingsListQuery.NOTIFY_ABOUT_NEW_FRIENDS));
		userSettings.setNotifyAboutAchievement(resultSet.getBoolean(SettingsListQuery.NOTIFY_ABOUT_ACHIEVEMENTS));

		return userSettings;
	}
}

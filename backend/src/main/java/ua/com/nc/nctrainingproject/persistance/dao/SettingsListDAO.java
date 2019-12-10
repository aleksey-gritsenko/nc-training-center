package ua.com.nc.nctrainingproject.persistance.dao;

import ua.com.nc.nctrainingproject.models.UserSettings;

public interface SettingsListDAO {

	UserSettings getUserSettingsById(int settingsId);

}

package ua.com.nc.nctrainingproject.persistance.dao;

import ua.com.nc.nctrainingproject.models.UserSettings;

public interface UserSettingsDAO {
    int getSettingsListId(int userId);

    UserSettings getSettingsListById(int userId);
}

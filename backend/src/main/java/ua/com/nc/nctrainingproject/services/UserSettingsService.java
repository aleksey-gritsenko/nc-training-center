package ua.com.nc.nctrainingproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.nc.nctrainingproject.models.UserSettings;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.UserSettingsPostgreDAO;

@Service
public class UserSettingsService {
    private final UserSettingsPostgreDAO userSettingsPostgreDAO;

    @Autowired
    public UserSettingsService(UserSettingsPostgreDAO userSettingsPostgreDAO) {
        this.userSettingsPostgreDAO = userSettingsPostgreDAO;
    }

    public UserSettings getUserSettings(int userId) {

        return userSettingsPostgreDAO.getSettingsListById(userId);
    }
    public void updateSettings(UserSettings userSettings, int userId) {
        userSettingsPostgreDAO.updateSettings(userId, userSettings);
    }

}

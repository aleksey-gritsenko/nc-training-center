package ua.com.nc.nctrainingproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.nc.nctrainingproject.models.UserSettings;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.UserSettingsPostgreDAO;
import ua.com.nc.nctrainingproject.services.utils.UserSettingsFinals;

import java.util.ArrayList;
import java.util.List;

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


    public Boolean toShowActivity(String actionName, UserSettings userSettings) {

        switch (actionName) {
            case  UserSettingsFinals.MARKED_BOOK_AS_READ: {
                if (userSettings.getBookNotification()) {
                    return true;
                }
                break;
            }
            case UserSettingsFinals.GOT_ACHIVEMENT: {
                if (userSettings.getNotifyAboutAchievement()) {
                    return true;
                }
                break;
            }
            case UserSettingsFinals.ADDED_ANNOUNCEMENT: {
                if (userSettings.getNotifyAboutAnnouncements()) {
                    return true;
                }
                break;
            }
            case UserSettingsFinals.ADDED_BOOK: {
                if (userSettings.getBookNotification()) {
                    return true;
                }
                break;
            }
            case UserSettingsFinals.ADDED_FRIEND: {
                if (userSettings.getNotifyAboutNewFriends()) {
                    return true;
                }
                break;
            }
            case UserSettingsFinals.WROTE_REWIEV: {
                if (userSettings.getSubscribeOnFriendReview()) {
                    return true;
                }
                break;
            }
            case UserSettingsFinals.MARKED_BOOK_AS_FAVOURITE: {
                if (userSettings.getBookNotification()) {
                    return true;
                }
                break;
            }
        }
        return false;
    }


}

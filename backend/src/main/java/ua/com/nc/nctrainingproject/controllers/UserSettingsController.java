package ua.com.nc.nctrainingproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.nc.nctrainingproject.models.UserSettings;
import ua.com.nc.nctrainingproject.services.UserSettingsService;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping
public class UserSettingsController {
	private final UserSettingsService userSettingsService;

	@Autowired
	public UserSettingsController(UserSettingsService userSettingsService) {
		this.userSettingsService = userSettingsService;
	}


    @RequestMapping(method = RequestMethod.GET, value = "/getSettings")
    public ResponseEntity<?> getSettings(@RequestParam(name = "userId") int userId) {
        UserSettings responce = userSettingsService.getUserSettings(userId);
        return Optional.ofNullable(responce).map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }
    


    @RequestMapping(value = "/updateSettings", method = RequestMethod.POST)
    public ResponseEntity<?> updateUserSettings(String subscribeOnFriends, String achivements, String bookNotification, String subscribeOnFriendReview, String notifyAboutNewFriends, String userId) {
        UserSettings userSettings = new UserSettings();
        userSettings.setNotifyAboutAnnouncements(Boolean.valueOf(subscribeOnFriends));
        userSettings.setAchievements(Boolean.valueOf(achivements));
        userSettings.setBookNotification(Boolean.valueOf(bookNotification));
        userSettings.setSubscribeOnFriendReview(Boolean.valueOf(subscribeOnFriendReview));
        userSettings.setNotifyAboutNewFriends(Boolean.valueOf(notifyAboutNewFriends));
        userSettings.setNotifyAboutAchievement(true);
        userSettingsService.updateSettings(userSettings,Integer.valueOf(userId));
        return new ResponseEntity<>(HttpStatus.CHECKPOINT);
    }

}

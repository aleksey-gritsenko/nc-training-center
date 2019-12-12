package ua.com.nc.nctrainingproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.nc.nctrainingproject.models.User;
import ua.com.nc.nctrainingproject.models.UserSettings;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.UserSettingsPostgreDAO;
import ua.com.nc.nctrainingproject.services.AuthorizationService;

import java.util.Optional;

@RestController
@CrossOrigin
public class AuthorizationController {
	private final AuthorizationService authorizationService;
	@Autowired
	UserSettingsPostgreDAO userSettingsPostgreDAO;

	@Autowired
	public AuthorizationController(AuthorizationService authorizationService) {
		this.authorizationService = authorizationService;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> login(@RequestParam(name = "login") String login,
								   @RequestParam(name = "password") String password) {
		User response = authorizationService.auth(login, password);
		return Optional.ofNullable(response).map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ResponseEntity<?> registration(@RequestParam(name = "login") String login,
										  @RequestParam(name = "password") String password,
										  @RequestParam(name = "email") String email) {
		//TODO situation when response = null is not watched
		User response = authorizationService.register(login, password, email);
		return Optional.ofNullable(response).map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public void test() {
		UserSettings userSettings = userSettingsPostgreDAO.getSettingsListById(5);
		System.out.println(userSettings.getAchievements());
		System.out.println(userSettings.getNotifyAboutNewFriends());
		System.out.println(userSettings.getBookNotification());
		System.out.println(userSettings.getNotifyAboutAchievement());
		System.out.println(userSettings.getSubscribeOnFriendReview());
		System.out.println(userSettings.getSubscribeOnFriends());
	}

}

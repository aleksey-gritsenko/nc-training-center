package ua.com.nc.nctrainingproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.com.nc.nctrainingproject.models.UserSettings;
import ua.com.nc.nctrainingproject.services.UserSettingsService;

@RestController
@CrossOrigin
@RequestMapping
public class UserSettingsController {
	private final UserSettingsService userSettingsService;

	@Autowired
	public UserSettingsController(UserSettingsService userSettingsService) {
		this.userSettingsService = userSettingsService;
	}

	@RequestMapping(value = "/getSettings", method = RequestMethod.POST)
	public ResponseEntity<?> getUserSettings(String userId) {
		System.out.println(userId);
		UserSettings response = userSettingsService.getUserSettings(Integer.valueOf(userId));
		System.out.println(response.toString());
		return response == null ? new ResponseEntity<>(HttpStatus.BAD_REQUEST) : ResponseEntity.ok(response);
	}

}

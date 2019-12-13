package ua.com.nc.nctrainingproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.com.nc.nctrainingproject.models.Activity;
import ua.com.nc.nctrainingproject.services.ActivityService;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@Controller
@RequestMapping("/activity")
public class ActivityController {
	private final ActivityService activityService;

	@Autowired
	public ActivityController(ActivityService activityService) {
		this.activityService = activityService;
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<Activity> getAllActivities() {
		return activityService.getAllActivities();
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<Activity> createActivity(@RequestBody Activity activity) {
		Activity response = activityService.createActivity(activity);
		return Optional.ofNullable(response).map(ResponseEntity::ok)
				.orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ResponseEntity<Activity> deleteActivity(@RequestBody Activity activity) {
		Activity response = activityService.deleteActivity(activity);
		return Optional.ofNullable(response).map(ResponseEntity::ok)
				.orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
	}

	@RequestMapping(value = "/getUser/{userId}", method = RequestMethod.GET)
	public List<String> getActivityByUserId(@PathVariable("userId") int userId) {
		return activityService.getActivityByUserID(userId);
	}
}

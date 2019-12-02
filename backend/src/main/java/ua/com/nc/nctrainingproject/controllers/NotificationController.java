package ua.com.nc.nctrainingproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.com.nc.nctrainingproject.models.Notification;
import ua.com.nc.nctrainingproject.services.NotificationService;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@Controller
@RequestMapping("/notification")
public class NotificationController {
	private final NotificationService notificationService;

	@Autowired
	public NotificationController(NotificationService notificationService) {
		this.notificationService = notificationService;
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getAllNotification() {
		return ResponseEntity.ok(notificationService.getAllNotifications());
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> createNotification(@RequestBody Notification notification) {
		Notification response = notificationService.createNotification(notification);
		return Optional.ofNullable(response).map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> deleteNotification(@RequestBody Notification notification) {
		Notification response = notificationService.deleteNotification(notification);
		return Optional.ofNullable(response).map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
	}

	@RequestMapping(value = "/get/{userId}", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> getNotificationByUserId(@PathVariable("userId") int userId) {
		return ResponseEntity.ok(notificationService.getNotificationByUserID(userId));
	}

	@RequestMapping(value = "/get/{actionId}", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> getNotificationByActionId(@PathVariable("actionId") int actionId) {
		return ResponseEntity.ok(notificationService.getNotificationByActionID(actionId));
	}

	@RequestMapping(value = "/get/{userId}_{actionId}", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> getNotificationByUserId(@RequestBody Notification notification) {
		return ResponseEntity.ok(notificationService.
				getNotificationByUserActionID(
				notification.getUserId(),
				notification.getActionId()));
	}
}

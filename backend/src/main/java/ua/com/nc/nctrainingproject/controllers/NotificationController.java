package ua.com.nc.nctrainingproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.com.nc.nctrainingproject.models.Notification;
import ua.com.nc.nctrainingproject.services.NotificationService;

import java.util.List;

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
	public List<Notification> getAllNotification() {
		return notificationService.getAllNotifications();
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public Notification createNotification(
			@RequestParam(name = "userId") int userId,
			@RequestParam(name = "actionId") int actionId) {
		Notification notification = new Notification(userId, actionId);
		notificationService.createNotification(notification);
		return notification;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public void deleteNotification(
			@RequestParam("userId") int userId,
			@RequestParam("actionId") int actionId) {
		notificationService.deleteNotification(userId, actionId);
	}

	@RequestMapping(value = "/get/{userId}", method = RequestMethod.POST)
	public Notification getNotificationByUserId(@PathVariable("userId") int userId) {
		return notificationService.getNotificationByUserID(userId);
	}

	@RequestMapping(value = "/get/{actionId}", method = RequestMethod.POST)
	public Notification getNotificationByActionId(@PathVariable("actionId") int actionId) {
		return notificationService.getNotificationByActionID(actionId);
	}

	@RequestMapping(value = "/get/{userId}_{actionId}", method = RequestMethod.POST)
	public Notification getNotificationByUserId(
			@PathVariable("userId") int userId,
			@PathVariable("actionId") int actionId) {
		return notificationService.getNotificationByUserActionID(userId, actionId);
	}
}

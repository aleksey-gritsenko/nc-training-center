package ua.com.nc.nctrainingproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.nc.nctrainingproject.models.User;
import ua.com.nc.nctrainingproject.services.UserService;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/update")
	ResponseEntity<?> update(@RequestParam(name = "login") String login,
							 @RequestParam(name = "newLogin") String newLogin,
							 @RequestParam(name = "newPassword") String newPassword,
							 @RequestParam(name = "newEmail") String newEmail) {
		User newData = new User(newLogin, newPassword, newEmail);
		return ResponseEntity.ok(userService.updateByName(login, newData));
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{userName}")
	ResponseEntity<?> get(@PathVariable(value = "userName") String userName) {
		return ResponseEntity.ok(userService.getByName(userName));
	}
}

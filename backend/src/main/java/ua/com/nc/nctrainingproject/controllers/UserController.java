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
							 @RequestParam(name = "newPassword") String newPassword,
							 @RequestParam(name = "newEmail") String newEmail) {
		User newData = new User(login, newPassword, newEmail);
		User response = userService.updateByName(newData);
		return response != null ? ResponseEntity.ok(response) : (ResponseEntity<?>) ResponseEntity.badRequest();
	}

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    ResponseEntity<?> get(@PathVariable(value = "id") int id) {
        return ResponseEntity.ok(userService.getById(id));
	}
}

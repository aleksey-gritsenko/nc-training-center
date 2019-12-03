package ua.com.nc.nctrainingproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.nc.nctrainingproject.models.User;
import ua.com.nc.nctrainingproject.services.UserService;

import java.util.List;
import java.util.Optional;

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
		return Optional.ofNullable(response).map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
	}

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    ResponseEntity<?> get(@PathVariable(value = "id") int id) {
        User response = userService.getById(id);
		return response != null ? ResponseEntity.ok(response) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/create/admin")
    ResponseEntity<?> createAdmin(@RequestBody User admin) {
        User response = userService.createAdmin(admin);
		return Optional.ofNullable(response).map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

	@RequestMapping(method = RequestMethod.POST, value = "/activate")
	public  ResponseEntity<?> activate(@RequestParam(name = "email") String email,
							 @RequestParam(name = "code") String code
							) {
		User response = userService.activateAccount(email,code);
		return Optional.ofNullable(response).map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}/deactivate")
	public ResponseEntity<?> deactivateAccount(@PathVariable(value = "id") int id) {
		return userService.deactivateAccount(id) ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/search/{searchName}")
	public ResponseEntity<?> searchUsers(@PathVariable(value = "searchName") String searchName) {
		List<User> response = userService.searchUsersByUsername(searchName);
		return Optional.ofNullable(response).map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
	}
}

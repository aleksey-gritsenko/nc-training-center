package ua.com.nc.nctrainingproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.nc.nctrainingproject.models.UserBook;
import ua.com.nc.nctrainingproject.services.UserBookService;

@RestController
@CrossOrigin
@RequestMapping("/userbook")
public class UserBookController {
	private final UserBookService userBookService;

	@Autowired
	public UserBookController(UserBookService userBookService) {
		this.userBookService = userBookService;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> addBookToUser(@RequestBody UserBook userBook) {
		UserBook response = userBookService.addBookToUser(userBook);
		return response != null ? ResponseEntity.ok(response) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}



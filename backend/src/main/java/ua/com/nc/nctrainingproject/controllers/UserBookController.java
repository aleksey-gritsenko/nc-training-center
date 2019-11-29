package ua.com.nc.nctrainingproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.nc.nctrainingproject.models.Book;
import ua.com.nc.nctrainingproject.models.UserBook;
import ua.com.nc.nctrainingproject.services.UserBookService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/userBook")
public class UserBookController {
	private final UserBookService userBookService;

	@Autowired
	public UserBookController(UserBookService userBookService) {
		this.userBookService = userBookService;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<?> addBookToUser(@RequestBody UserBook userBook) {
		UserBook response = userBookService.addBookToUser(userBook);
		//return Optional.ofNullable(response).ifPresentOrElse(ResponseEntity::ok(response),ResponseEntity::badRequest(HttpStatus.BAD_REQUEST);
		return response != null ? ResponseEntity.ok(response) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<?> getAllUsersBooks(@RequestBody UserBook userBook) {
		List<Book> response = userBookService.getAllUserBooks(userBook);
		return response != null ? ResponseEntity.ok(response) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/all/favourite", method = RequestMethod.GET)
	public ResponseEntity<?> getAllFavouriteBooks(@RequestBody UserBook userBook) {
		List<Book> response = userBookService.getAllFavouriteBooks(userBook);
		return response != null ? ResponseEntity.ok(response) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/all/read", method = RequestMethod.GET)
	public ResponseEntity<?> getAllReadBooks(@RequestBody UserBook userBook) {
		List<Book> response = userBookService.getAllReadBooks(userBook);
		return response != null ? ResponseEntity.ok(response) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/markread", method = RequestMethod.GET)
	public ResponseEntity<?> markBookAsRead(@RequestBody UserBook userBook) {
		UserBook response = userBookService.markBookAsRead(userBook);
		return response != null ? ResponseEntity.ok(response) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/markfav", method = RequestMethod.GET)
	public ResponseEntity<?> markBookAsFavourite(@RequestBody UserBook userBook) {
		UserBook response = userBookService.markBookAsFavourite(userBook);
		return response != null ? ResponseEntity.ok(response) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/remove_read", method = RequestMethod.GET)
	public ResponseEntity<?> removeFromRead(@RequestBody UserBook userBook) {
		UserBook response = userBookService.removeFromRead(userBook);
		return response != null ? ResponseEntity.ok(response) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/remove_fav", method = RequestMethod.GET)
	public ResponseEntity<?> removeFromFavourite(@RequestBody UserBook userBook) {
		UserBook response = userBookService.removeFromFavourite(userBook);
		return response != null ? ResponseEntity.ok(response) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}



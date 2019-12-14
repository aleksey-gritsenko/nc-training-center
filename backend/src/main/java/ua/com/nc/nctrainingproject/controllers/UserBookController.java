package ua.com.nc.nctrainingproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.nc.nctrainingproject.models.Book;
import ua.com.nc.nctrainingproject.models.UserBook;
import ua.com.nc.nctrainingproject.services.UserBookService;

import java.util.List;
import java.util.Optional;

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
	public ResponseEntity<UserBook> addBookToUser(@RequestBody UserBook userBook) {
		UserBook response = userBookService.addBookToUser(userBook);
		return Optional.ofNullable(response).map(ResponseEntity::ok)
				.orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
	}

	@RequestMapping(value = "/getById", method = RequestMethod.GET)
	public ResponseEntity<UserBook> getUserBookById(@RequestBody UserBook userBook) {
		UserBook response = userBookService.getUserBookByBookUserId(userBook);
		return Optional.ofNullable(response).map(ResponseEntity::ok)
				.orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<Book> getAllUsersBooks(@RequestParam(name = "userId") int userId) {
		return userBookService.getAllUserBooks(userId);
	}

	@RequestMapping(value = "/all/favourite", method = RequestMethod.GET)
	public List<Book> getAllFavouriteBooks(@RequestParam(name = "userId") int userId) {
		return userBookService.getAllFavouriteBooks(userId);
	}

	@RequestMapping(value = "/all/read", method = RequestMethod.GET)
	public List<Book> getAllReadBooks(@RequestParam(name = "userId") int userId) {
		return userBookService.getAllReadBooks(userId);
	}

	@RequestMapping(value = "/mark_read", method = RequestMethod.POST)
	public ResponseEntity<UserBook> markBookAsRead(@RequestBody UserBook userBook) {
		UserBook response = userBookService.markBookAsRead(userBook);
		return Optional.ofNullable(response).map(ResponseEntity::ok)
				.orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
	}

	@RequestMapping(value = "/mark_fav", method = RequestMethod.POST)
	public ResponseEntity<UserBook> markBookAsFavourite(@RequestBody UserBook userBook) {
		UserBook response = userBookService.markBookAsFavourite(userBook);
		return Optional.ofNullable(response).map(ResponseEntity::ok)
				.orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
	}

	@RequestMapping(value = "/remove_read", method = RequestMethod.POST)
	public ResponseEntity<UserBook> removeFromRead(@RequestBody UserBook userBook) {
		UserBook response = userBookService.removeFromRead(userBook);
		return Optional.ofNullable(response).map(ResponseEntity::ok)
				.orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
	}

	@RequestMapping(value = "/remove_fav", method = RequestMethod.POST)
	public ResponseEntity<UserBook> removeFromFavourite(@RequestBody UserBook userBook) {
		UserBook response = userBookService.removeFromFavourite(userBook);
		return Optional.ofNullable(response).map(ResponseEntity::ok)
				.orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ResponseEntity<UserBook> deleteBookFromAdded(@RequestBody UserBook userBook) {
		UserBook response = userBookService.deleteBookFromAdded(userBook);
		return Optional.ofNullable(response).map(ResponseEntity::ok)
				.orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
	}

	@RequestMapping(value = "/getAllById", method = RequestMethod.GET)
	public List<UserBook> getAllUserBooksByUserId(@RequestParam(name = "userId") int userId) {
		return userBookService.getAllUserBooksByUserId(userId);
	}
}



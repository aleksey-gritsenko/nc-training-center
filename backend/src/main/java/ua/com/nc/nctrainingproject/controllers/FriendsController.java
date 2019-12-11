package ua.com.nc.nctrainingproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.nc.nctrainingproject.models.User;
import ua.com.nc.nctrainingproject.services.FriendsService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/friends")

public class FriendsController {
	@Autowired
	private final FriendsService friendsService;

	public FriendsController(FriendsService friendsService) {
		this.friendsService = friendsService;
	}

	@RequestMapping(value = "/send", method = RequestMethod.POST)
	public ResponseEntity<?> sendRequest(@RequestParam int sender, @RequestParam int reciever) {
      /*  if(friendsService.checkRequest(sender,reciever)){
        friendsService.sendRequest(sender,reciever);
            return new ResponseEntity<>(HttpStatus.OK);

       }
       return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

       */


		friendsService.sendRequest(sender, reciever);
		return new ResponseEntity<>(HttpStatus.OK);

	}

	@RequestMapping(value = "/accept", method = RequestMethod.POST)
	public void acceptRequest(@RequestParam int sender, @RequestParam int reciever) {
		friendsService.aceptRequest(sender, reciever);
	}

	@RequestMapping(value = "all", method = RequestMethod.GET)
	public List<User> allFriends(@RequestParam int id) {
		return friendsService.getAllFriends(id);

	}

	@RequestMapping(value = "new", method = RequestMethod.GET)
	public List<User> allNewFriends(@RequestParam int id) {
		return friendsService.getAllNewRequests(id);

	}

}

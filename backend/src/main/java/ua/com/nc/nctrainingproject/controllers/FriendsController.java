package ua.com.nc.nctrainingproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.com.nc.nctrainingproject.services.FriendsService;

@RestController
@CrossOrigin
@RequestMapping("/friends")

public class FriendsController {
    @Autowired
    private final FriendsService friendsService;
    public FriendsController(FriendsService friendsService){
        this.friendsService = friendsService;
    }

    @RequestMapping(value = "/send" ,method = RequestMethod.POST)
    public void sendRequest(@RequestParam int id,@RequestParam int id2){
        friendsService.sendRequest(id,id2);
    }

    @RequestMapping(value = "/accept",method = RequestMethod.POST)
    public void acceptRequest(@RequestParam int id,@RequestParam int id2){
        friendsService.aceptRequest(id,id2);
    }
}

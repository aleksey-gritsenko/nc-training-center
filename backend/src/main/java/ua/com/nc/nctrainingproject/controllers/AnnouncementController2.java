package ua.com.nc.nctrainingproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin

public class AnnouncementController2 {
   @RequestMapping(value = "/myannouncement", method = RequestMethod.POST)
    public void getAnnouncement(@RequestBody   Announcement2 announcement){
        System.out.println(announcement.toString());
    }
@Autowired
AnnouncementService3 announcementService3;
    @RequestMapping(value = "/myallannouncement", method = RequestMethod.GET)
    public List<Announcement2> getAllAnnouncement(){
        return announcementService3.method();
    }




}

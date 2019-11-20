package ua.com.nc.nctrainingproject.controllers;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin

public class AnnouncementController2 {
  @RequestMapping(value = "/myannouncement", method = RequestMethod.POST)

 /* public void getAnnouncement(@RequestParam(name = "title") String title,
                              @RequestParam(name = "description") String description,
                              @RequestParam(name = "date") String date){
    Announcement2 announcement = new Announcement2(title,description,date);
  }


  */

    public void getAnnouncement(Announcement2 announcement){
   System.out.println(announcement);
   }

  @RequestMapping(value = "/myallannouncement", method = RequestMethod.GET)
  public List<Announcement2> getAllAnnouncement(){
    return new Announcement2().method();
  }
}

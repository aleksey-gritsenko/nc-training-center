package ua.com.nc.nctrainingproject.controllers;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin

public class AnnouncementController {
   @RequestMapping(value = "/announcement", method = RequestMethod.POST)

  public void getAnnouncement(@RequestParam(name = "title") String title,
                              @RequestParam(name = "description") String description,
                              @RequestParam(name = "date") String date){
      Announcement announcement = new Announcement(title,description,date);
  }


  /**  public void getAnnouncement(Announcement announcement){
      System.out.println(announcement);
    }
   */
  @RequestMapping(value = "/allannouncement", method = RequestMethod.GET)
  public List<Announcement> getAllAnnouncement(){
    return new Announcement().method();
  }
}

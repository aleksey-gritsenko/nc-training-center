package ua.com.nc.nctrainingproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.com.nc.nctrainingproject.models.Announcement;
import ua.com.nc.nctrainingproject.services.AnnouncementService;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/announcements")
public class AnnouncementsController {

  private final AnnouncementService announcementService;

  @Autowired
  public AnnouncementsController(AnnouncementService announcementService){
    this.announcementService = announcementService;
  }

  @RequestMapping(method = RequestMethod.GET)
  public List<Announcement> getPublishedAnnouncements(){
    return announcementService.getPublishedAnnouncements();
  }

  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public List<Announcement> getUnpublishedAnnouncements(){
    return announcementService.getUnpublishedAnnouncements();
  }

  @RequestMapping(value = "/all", method = RequestMethod.GET)
  public List<Announcement> getAllAnnouncements(){
    return announcementService.getAnnouncements();
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public Announcement getAnnouncement(@PathVariable("id") int id){
    return announcementService.getAnnouncement(id);
  }

  @RequestMapping(value = "/newAnnouncement", method = RequestMethod.POST)
  public void createAnnouncement(
    @RequestParam(name = "description") String description,
    @RequestParam(name = "announcementDate") Date announcementDate,
    @RequestParam(name = "bookID") int bookID,
    @RequestParam(name = "priority") String priority,
    @RequestParam(name = "adminID") int adminID,
    @RequestParam(name = "status") String status
  ){
    Announcement announcement = new Announcement(description, announcementDate, bookID, priority, adminID, status);
    announcementService.createAnnouncement(announcement);
  }

  @RequestMapping(value = "/proposeAnnouncement", method = RequestMethod.POST)
  public void proposeAnnouncement(
    @RequestParam(name = "description") String description,
    @RequestParam(name = "announcementDate") Date announcementDate,
    @RequestParam(name = "bookID") int bookID,
    @RequestParam(name = "priority") String priority,
    @RequestParam(name = "adminID") int adminID
  ){
    Announcement announcement = new Announcement(description, announcementDate, bookID, priority, adminID);
    announcementService.proposeAnnouncement(announcement);
  }

  @RequestMapping(value = "/new/{id}", method = RequestMethod.POST)
  public void publishAnnouncement(@PathVariable("id") int id){
    announcementService.publishAnnouncement(id);
  }

  @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
  public void deleteAnnouncement(@PathVariable("id") int id){
    announcementService.deleteAnnouncement(id);
  }

  @RequestMapping(value = "/update", method = RequestMethod.PUT)
  public void updateAnnouncement(
    @RequestParam(name = "description") String description,
    @RequestParam(name = "announcementDate") Date announcementDate,
    @RequestParam(name = "bookID") int bookID,
    @RequestParam(name = "priority") String priority,
    @RequestParam(name = "adminID") int adminID,
    @RequestParam(name = "status") String status
  ){
    Announcement announcement = new Announcement(description, announcementDate, bookID, priority, adminID, status);
    announcementService.updateAnnouncement(announcement);
  }
}

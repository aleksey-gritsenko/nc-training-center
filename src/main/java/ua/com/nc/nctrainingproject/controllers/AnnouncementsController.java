package ua.com.nc.nctrainingproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.com.nc.nctrainingproject.models.Announcement;
import ua.com.nc.nctrainingproject.services.AnnouncementService;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
public class AnnouncementsController {

  private final AnnouncementService announcementService;

  @Autowired
  public AnnouncementsController(AnnouncementService announcementService){
    this.announcementService = announcementService;
  }

  @RequestMapping(value = "/announcements", method = RequestMethod.GET)
  public List<Announcement> getPublishedAnnouncements(){
    return announcementService.getPublishedAnnouncements();
  }

  @RequestMapping(value = "/announcements/new", method = RequestMethod.GET)
  public List<Announcement> getUnpublishedAnnouncements(){
    return announcementService.getUnpublishedAnnouncements();
  }

  @RequestMapping(value = "/announcements/all", method = RequestMethod.GET)
  public List<Announcement> getAllAnnouncements(){
    return announcementService.getAnnouncements();
  }

  @RequestMapping(value = "/announcements/{id}", method = RequestMethod.GET)
  public Announcement getAnnouncement(@PathVariable("id") int id){
    return announcementService.getAnnouncement(id);
  }

  @RequestMapping(value = "/announcements/newAnnouncement", method = RequestMethod.POST)
  public Announcement createAnnouncement(
    @RequestParam(name = "description") String description,
    @RequestParam(name = "announcementDate") Date announcementDate,
    @RequestParam(name = "bookID") int bookID,
    @RequestParam(name = "priority") String priority,
    @RequestParam(name = "adminID") int adminID,
    @RequestParam(name = "status") String status
  )  {
    Announcement announcement = new Announcement(description, announcementDate, bookID, priority, adminID, status);
    announcementService.createAnnouncement(announcement);
    return announcement;
  }

  @RequestMapping(value = "/announcements/proposeAnnouncement", method = RequestMethod.POST)
  public Announcement proposeAnnouncement(
    @RequestParam(name = "description") String description,
    @RequestParam(name = "announcementDate") Date announcementDate,
    @RequestParam(name = "bookID") int bookID,
    @RequestParam(name = "priority") String priority,
    @RequestParam(name = "adminID") int adminID
  )  {
    Announcement announcement = new Announcement(description, announcementDate, bookID, priority, adminID);
    announcementService.proposeAnnouncement(announcement);
    return announcement;
  }

  @RequestMapping(value = "/announcements/new/{id}", method = RequestMethod.POST)
  public void publishAnnouncement(@PathVariable("id") int id){ announcementService.publishAnnouncement(id); }

  @RequestMapping(value = "/announcements/delete/{id}", method = RequestMethod.POST)
  public void deleteAnnouncement(@PathVariable("id") int id){
    announcementService.deleteAnnouncement(id);
  }

  @RequestMapping(value = "/announcements/update", method = RequestMethod.POST)
  public Announcement updateAnnouncement(
    @RequestParam(name = "announcementId") int announcementID,
    @RequestParam(name = "description") String description,
    @RequestParam(name = "announcementDate") Date announcementDate,
    @RequestParam(name = "bookID") int bookID,
    @RequestParam(name = "priority") String priority,
    @RequestParam(name = "adminID") int adminID,
    @RequestParam(name = "status") String status
  ) {
    Announcement announcement = new Announcement(announcementID, description, announcementDate,
      bookID, priority, adminID, status);
    announcementService.updateAnnouncement(announcement);
    return announcement;
  }
}

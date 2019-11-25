package ua.com.nc.nctrainingproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.com.nc.nctrainingproject.models.Announcement;
import ua.com.nc.nctrainingproject.services.AnnouncementService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/announcements")
public class AnnouncementController {

	private final AnnouncementService announcementService;

	@Autowired
	public AnnouncementController(AnnouncementService announcementService) {
		this.announcementService = announcementService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Announcement> getPublishedAnnouncements() {
		return announcementService.getPublishedAnnouncements();
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public List<Announcement> getUnpublishedAnnouncements() {
		return announcementService.getUnpublishedAnnouncements();
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<Announcement> getAllAnnouncements() {
		return announcementService.getAnnouncements();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Announcement getAnnouncement(@PathVariable("id") int id) {
		return announcementService.getAnnouncement(id);
	}

	@RequestMapping(value = "/newAnnouncement", method = RequestMethod.POST)
	public Announcement createAnnouncement(@RequestBody Announcement announcement){
		announcementService.createAnnouncement(announcement);
		return announcement;
	}

	@RequestMapping(value = "/proposeAnnouncement", method = RequestMethod.POST)
	public Announcement proposeAnnouncement(@RequestBody Announcement announcement){
		announcementService.proposeAnnouncement(announcement);
		System.out.println(announcement.toString());

		return announcement;
	}
/*
	@RequestMapping(value = "/new/{id}", method = RequestMethod.POST)
	public void publishAnnouncement(@PathVariable("id") int id) {
		announcementService.publishAnnouncement(id);
	}

 */

	@RequestMapping(value = "/publish", method = RequestMethod.POST)
	public void publishAnnouncement(@RequestBody Announcement announcement) {
		System.out.println("id "+announcement.getId());
		announcementService.publishAnnouncement(announcement.getId());
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public void deleteAnnouncement(@PathVariable("id") int id) {
		announcementService.deleteAnnouncement(id);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Announcement updateAnnouncement(@RequestBody Announcement announcement){
		announcementService.updateAnnouncement(announcement);
		return announcement;
	}
}

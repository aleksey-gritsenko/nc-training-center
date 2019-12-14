package ua.com.nc.nctrainingproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.nc.nctrainingproject.models.Announcement;
import ua.com.nc.nctrainingproject.services.AnnouncementService;

import java.util.List;
import java.util.Optional;

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
	public ResponseEntity<List<Announcement>> getPublishedAnnouncements() {
		List<Announcement> response = announcementService.getPublishedAnnouncements();
		return Optional.ofNullable(response).map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ResponseEntity<List<Announcement>> getUnpublishedAnnouncements() {
		List<Announcement> response = announcementService.getUnpublishedAnnouncements();
		return Optional.ofNullable(response).map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<Announcement>> getAllAnnouncements() {
		List<Announcement> response = announcementService.getAnnouncements();
		return Optional.ofNullable(response).map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Announcement> getAnnouncement(@PathVariable("id") int id) {
		Announcement response = announcementService.getAnnouncement(id);
		return Optional.ofNullable(response).map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
	}

	@RequestMapping(value = "/newAnnouncement", method = RequestMethod.POST)
	public ResponseEntity<Announcement> createAnnouncement(@RequestBody Announcement announcement) {
		Announcement response = announcementService.createAnnouncement(announcement);
		return Optional.ofNullable(response).map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
	}

	@RequestMapping(value = "/proposeAnnouncement", method = RequestMethod.POST)
	public ResponseEntity<Announcement> proposeAnnouncement(@RequestBody Announcement announcement) {
		Announcement response = announcementService.proposeAnnouncement(announcement);
		return Optional.ofNullable(response).map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
	}

	@RequestMapping(value = "/publish", method = RequestMethod.POST)
	public ResponseEntity<Announcement>  publishAnnouncement(@RequestBody Announcement announcement) {
		Announcement response = announcementService.publishAnnouncement(announcement);
		return Optional.ofNullable(response).map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public ResponseEntity<Announcement> deleteAnnouncement(@PathVariable("id") int id) {
		Announcement response = announcementService.deleteAnnouncement(id);
		return Optional.ofNullable(response).map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity<Announcement>  updateAnnouncement(@RequestBody Announcement announcement) {
		Announcement response = announcementService.updateAnnouncement(announcement);
		return Optional.ofNullable(response).map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
	}
}

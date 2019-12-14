package ua.com.nc.nctrainingproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.nc.nctrainingproject.models.Announcement;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.AnnouncementPostgreDAO;

import java.util.List;

@Service
public class AnnouncementService {
	private final AnnouncementPostgreDAO announcementsPostgreDAO;
	private final ActionService actionService;

	@Autowired
	public AnnouncementService(AnnouncementPostgreDAO announcementsPostgreDAO, ActionService actionService) {
		this.announcementsPostgreDAO = announcementsPostgreDAO;
		this.actionService = actionService;
	}

	public Announcement getAnnouncement(int id) {
		return announcementsPostgreDAO.getAnnouncementByID(id);
	}

	public List<Announcement> getAnnouncements() {
		return announcementsPostgreDAO.getAnnouncements();
	}

	public List<Announcement> getPublishedAnnouncements() {
		return announcementsPostgreDAO.getPublishedAnnouncements();
	}

	public List<Announcement> getUnpublishedAnnouncements() {
		return announcementsPostgreDAO.getUnpublishedAnnouncements();
	}

	public Announcement createAnnouncement(Announcement announcement) {
		if (announcementsPostgreDAO.getAnnouncementByID(announcement.getAnnouncementID()) == null){
			announcementsPostgreDAO.createAnnouncement(announcement);
		}
		return announcement;
	}

	public Announcement proposeAnnouncement(Announcement announcement) {
		if (announcementsPostgreDAO.getAnnouncementByID(announcement.getAnnouncementID()) == null){
			announcementsPostgreDAO.proposeAnnouncement(announcement);
		}
		return announcement;
	}

	public Announcement publishAnnouncement(Announcement announcement) {
		if (announcementsPostgreDAO.getAnnouncementByID(announcement.getAnnouncementID()) != null) {
			actionService.addNewAction(announcement.getOwnerId(), 2);
			announcementsPostgreDAO.publishAnnouncement(announcement);
		}
		return announcement;
	}

	public Announcement deleteAnnouncement(int id) {
		Announcement announcement = announcementsPostgreDAO.getAnnouncementByID(id);
		if (announcement != null) {
			announcementsPostgreDAO.deleteAnnouncement(announcement.getAnnouncementID());
		}
		return announcement;
	}

	public Announcement updateAnnouncement(Announcement announcement) {
		if (announcementsPostgreDAO.getAnnouncementByID(announcement.getAnnouncementID()) != null){
			announcementsPostgreDAO.updateAnnouncement(announcement);
		}
		return announcement;
	}
}

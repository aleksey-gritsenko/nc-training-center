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
	/*	//test
		List<Announcement> res = new ArrayList<>();
		Announcement an = new Announcement();
		an.setAdmin_id(1);
		an.setAnnouncementDate(new  Date());
		an.setBookID(1);
		an.setAnnouncementID(1);
		an.setDescription("TEST");
		res.add(an);
		return res;
		//end test*/
		return announcementsPostgreDAO.getPublishedAnnouncements();
	}

	public List<Announcement> getUnpublishedAnnouncements() {
		return announcementsPostgreDAO.getUnpublishedAnnouncements();
	}

	public void createAnnouncement(Announcement announcement) {
		if (announcementsPostgreDAO.getAnnouncementByID(announcement.getAnnouncementID()) != null)
			return;
		announcementsPostgreDAO.createAnnouncement(announcement);
	}

	public void proposeAnnouncement(Announcement announcement) {
		if (announcementsPostgreDAO.getAnnouncementByID(announcement.getAnnouncementID()) != null)
			return;
		announcementsPostgreDAO.proposeAnnouncement(announcement);
	}

	public void publishAnnouncement(Announcement announcement) {
		actionService.addNewAction(announcement.getOwnerId(), 2);
		if (announcementsPostgreDAO.getAnnouncementByID(announcement.getAnnouncementID()) == null)
			return;
		announcementsPostgreDAO.publishAnnouncement(announcement);
	}

	public void deleteAnnouncement(int id) {
		if (announcementsPostgreDAO.getAnnouncementByID(id) == null)
			return;
		announcementsPostgreDAO.deleteAnnouncement(id);
	}

	public void updateAnnouncement(Announcement announcement) {
		if (announcementsPostgreDAO.getAnnouncementByID(announcement.getAnnouncementID()) == null)
			return;
		announcementsPostgreDAO.updateAnnouncement(announcement);
	}
}

package ua.com.nc.nctrainingproject.persistance.dao;

import ua.com.nc.nctrainingproject.models.Announcement;

import java.util.List;

public interface AnnouncementsDAO {
	Announcement getAnnouncementByID(int id);

	List<Announcement> getAnnouncements();

	void deleteAnnouncement(int id);

	void createAnnouncement(Announcement announcement);

	void publishAnnouncement(int id);

	void updateAnnouncement(int id, Announcement announcement);
}

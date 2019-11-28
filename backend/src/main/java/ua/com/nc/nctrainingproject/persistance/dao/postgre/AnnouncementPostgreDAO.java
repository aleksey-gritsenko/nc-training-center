package ua.com.nc.nctrainingproject.persistance.dao.postgre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.com.nc.nctrainingproject.models.Announcement;
import ua.com.nc.nctrainingproject.persistance.dao.AbstractDAO;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.AnnouncementQuery;
import ua.com.nc.nctrainingproject.persistance.mappers.AnnouncementRowMapper;

import java.util.List;

@Repository
public class AnnouncementPostgreDAO extends AbstractDAO<Announcement> {

	@Autowired
	public AnnouncementPostgreDAO(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate);
	}

	public Announcement getAnnouncementByID(int id) {
		return super.getEntityById(AnnouncementQuery.GET_ANNOUNCEMENT_BY_ID,
				new AnnouncementRowMapper(), id);
	}

	public List<Announcement> getAnnouncements() {
		return super.getAllEntities(AnnouncementQuery.GET_ANNOUNCEMENTS,
				new AnnouncementRowMapper());
	}

	public List<Announcement> getPublishedAnnouncements() {
		return super.getAllEntities(AnnouncementQuery.GET_PUBLISHED_ANNOUNCEMENTS,
				new AnnouncementRowMapper());
	}

	public List<Announcement> getUnpublishedAnnouncements() {
		return super.getAllEntities(AnnouncementQuery.GET_UNPUBLISHED_ANNOUNCEMENTS,
				new AnnouncementRowMapper());
	}

	public void deleteAnnouncement(int id) {
		super.deleteEntityById(AnnouncementQuery.DELETE_ANNOUNCEMENT_BY_ID, id);
	}

	public void createAnnouncement(Announcement announcement) {
		jdbcTemplate.update(AnnouncementQuery.CREATE_ANNOUNCEMENT, announcement.getDescription(),
				announcement.getAnnouncementDate(), announcement.getBookID(),
				announcement.getPriority(), announcement.getOwnerId(),
				announcement.getStatus(), announcement.getAdmin_id());
	}

	public void proposeAnnouncement(Announcement announcement) {
		//announcement.setStatus(AnnouncementQuery.UNPUBLISHED);
		//announcement.setPriority(AnnouncementQuery.LOW_PRIORITY);

		jdbcTemplate.update(AnnouncementQuery.CREATE_ANNOUNCEMENT, announcement.getDescription(),
				announcement.getAnnouncementDate(), announcement.getBookID(),
				announcement.getPriority(), announcement.getOwnerId(),
				announcement.getStatus(), announcement.getAdmin_id());
	}

	public void publishAnnouncement(Announcement announcement) {
		jdbcTemplate.update(AnnouncementQuery.PUBLISH_ANNOUNCEMENT, announcement.getAdmin_id(),
				announcement.getAnnouncementID());
	}

	public void updateAnnouncement(Announcement announcement) {
		Object[] params = new Object[]{announcement.getDescription(), announcement.getAnnouncementDate(),
				announcement.getBookID(), announcement.getPriority(), announcement.getOwnerId(), announcement.getStatus(),
				announcement.getAnnouncementID(), announcement.getAdmin_id()};
		super.updateEntityById(announcement.getAnnouncementID(), params, AnnouncementQuery.UPDATE_ANNOUNCEMENT);
	}
}

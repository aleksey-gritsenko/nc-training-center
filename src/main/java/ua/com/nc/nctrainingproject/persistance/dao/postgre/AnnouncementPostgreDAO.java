package ua.com.nc.nctrainingproject.persistance.dao.postgre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.com.nc.nctrainingproject.models.Announcement;
import ua.com.nc.nctrainingproject.persistance.dao.AbstractDAO;
import ua.com.nc.nctrainingproject.persistance.dao.AnnouncementsDAO;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.AnnouncementQuery;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.BookQuery;
import ua.com.nc.nctrainingproject.persistance.mappers.AnnouncementRowMapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class AnnouncementPostgreDAO extends AbstractDAO<Announcement>{

   @Autowired
   public AnnouncementPostgreDAO(JdbcTemplate jdbcTemplate){
     super(jdbcTemplate);
   }

  public Announcement getAnnouncementByID(int id){
    Announcement announcement = super.getEntityById(AnnouncementQuery.GET_ANNOUNCEMENT_BY_ID,
                                                    new AnnouncementRowMapper(), id);
    return announcement;
  }

  public List<Announcement> getAnnouncements(){
    List<Announcement> announcements = super.getAllEntities(AnnouncementQuery.GET_ANNOUNCEMENTS,
                                                            new AnnouncementRowMapper());
    return announcements;
  }

  public List<Announcement> getPublishedAnnouncements(){
    List<Announcement> announcements = super.getAllEntities(AnnouncementQuery.GET_PUBLISHED_ANNOUNCEMENTS,
      new AnnouncementRowMapper());
    return announcements;
  }

  public List<Announcement> getUnpublishedAnnouncements(){
    List<Announcement> announcements = super.getAllEntities(AnnouncementQuery.GET_UNPUBLISHED_ANNOUNCEMENTS,
      new AnnouncementRowMapper());
    return announcements;
  }

  public void deleteAnnouncement(int id){
    super.deleteEntityById(AnnouncementQuery.DELETE_ANNOUNCEMENT_BY_ID, id);
  }

  public void createAnnouncement(Announcement announcement){
    jdbcTemplate.update(AnnouncementQuery.CREATE_ANNOUNCEMENT, announcement.getDescription(), announcement.getBookID(),
      announcement.getPriority(), announcement.getAdminID(), announcement.getStatus());
  }

  public void proposeAnnouncement(Announcement announcement){
    jdbcTemplate.update(AnnouncementQuery.PROPOSE_ANNOUNCEMENT, announcement.getDescription(), announcement.getBookID(),
      announcement.getPriority(), announcement.getAdminID());
  }

  public void publishAnnouncement(int id){
    jdbcTemplate.update(AnnouncementQuery.PUBLISH_ANNOUNCEMENT, id);
  }

  public void updateAnnouncement(Announcement announcement){
    Object[] params = new Object[]{
      announcement.getDescription(), announcement.getAnnouncementDate(),
      announcement.getBookID(), announcement.getPriority(), announcement.getAdminID(), announcement.getStatus(),
      announcement.getAnnouncementID() };
    super.updateEntityById(announcement.getAnnouncementID(), params, AnnouncementQuery.UPDATE_ANNOUNCEMENT);
  }
}

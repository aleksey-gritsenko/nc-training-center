package ua.com.nc.nctrainingproject.services;

import com.sun.org.apache.xpath.internal.objects.XObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.nc.nctrainingproject.models.Announcement;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.AnnouncementPostgreDAO;

import java.util.List;

@Service
public class AnnouncementService {

  private final AnnouncementPostgreDAO announcementsPostgreDAO;

  @Autowired
  public AnnouncementService(AnnouncementPostgreDAO announcementsPostgreDAO){
    this.announcementsPostgreDAO = announcementsPostgreDAO;
  }

  public Announcement getAnnouncement(int id){
    return announcementsPostgreDAO.getAnnouncementByID(id);
  }

  public List<Announcement> getAnnouncements(){
    return announcementsPostgreDAO.getAnnouncements();
  }

  public List<Announcement> getPublishedAnnouncements(){
    return announcementsPostgreDAO.getPublishedAnnouncements();
  }

  public List<Announcement> getUnpublishedAnnouncements(){
    return announcementsPostgreDAO.getUnpublishedAnnouncements();
  }

  public void createAnnouncement(Announcement announcement){
    if(announcementsPostgreDAO.getAnnouncementByID(announcement.getAnnouncementID()) != null)
      return;
    announcementsPostgreDAO.createAnnouncement(announcement);
  }

  public void proposeAnnouncement(Announcement announcement){
    if(announcementsPostgreDAO.getAnnouncementByID(announcement.getAnnouncementID()) != null)
      return;
    announcementsPostgreDAO.proposeAnnouncement(announcement);
  }

  public void publishAnnouncement(int id){
    if(announcementsPostgreDAO.getAnnouncementByID(id) == null)
      return;
    announcementsPostgreDAO.publishAnnouncement(id);
  }

  public void deleteAnnouncement(int id){
    if(announcementsPostgreDAO.getAnnouncementByID(id) == null)
      return;
    announcementsPostgreDAO.deleteAnnouncement(id);
  }

  public void updateAnnouncement(Announcement announcement){
    if(announcementsPostgreDAO.getAnnouncementByID(announcement.getAnnouncementID()) == null)
      return;
    announcementsPostgreDAO.updateAnnouncement(announcement);
  }
}

package ua.com.nc.nctrainingproject.models;

import java.util.Date;

public class Announcement extends Entity {
	private String description;
	private Date announcementDate;
	private int bookID;
	private String priority;
	private int adminID;
	private String status;

	public Announcement() {
	}

	public Announcement(String description, Date announcementDate,
						int bookID, String priority) {
		this.description = description;
		this.announcementDate = announcementDate;
		this.bookID = bookID;
		this.priority = priority;
	}

	public Announcement(String description, Date announcementDate,
						int bookID, String priority, int adminID) {
		this.description = description;
		this.announcementDate = announcementDate;
		this.bookID = bookID;
		this.priority = priority;
		this.adminID = adminID;
	}

	public Announcement(String description, Date announcementDate,
						int bookID, String priority, int adminID, String status) {
		this.description = description;
		this.announcementDate = announcementDate;
		this.bookID = bookID;
		this.priority = priority;
		this.adminID = adminID;
		this.status = status;
	}

	public Announcement(int announcementID, String description, Date announcementDate,
						int bookID, String priority, int adminID, String status) {
		super(announcementID);
		this.description = description;
		this.announcementDate = announcementDate;
		this.bookID = bookID;
		this.priority = priority;
		this.adminID = adminID;
		this.status = status;
	}

	public int getAnnouncementID() {
		return super.getId();
	}

	public void setAnnouncementID(int id) {
		super.setId(id);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getAnnouncementDate() {
		return announcementDate;
	}

	public void setAnnouncementDate(Date announcementDate) {
		this.announcementDate = announcementDate;
	}

	public int getBookID() {
		return bookID;
	}

	public void setBookID(int bookID) {
		this.bookID = bookID;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public int getAdminID() {
		return adminID;
	}

	public void setAdminID(int adminID) {
		this.adminID = adminID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Announcement{" +
				", description='" + description + '\'' +
				", announcementDate=" + announcementDate +
				", bookID=" + bookID +
				", priority='" + priority + '\'' +
				", adminID=" + adminID +
				", status='" + status + '\'' +
				'}';
	}
}

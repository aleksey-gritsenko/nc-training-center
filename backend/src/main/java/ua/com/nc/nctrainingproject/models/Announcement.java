package ua.com.nc.nctrainingproject.models;

import java.util.Date;

public class Announcement extends Entity {
	private String description;
	private Date announcementDate;
	private int bookID;
	private String priority;
	private int ownerId;
	private String status;



	private int admin_id;

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
						int bookID, String priority, int ownerId) {
		this.description = description;
		this.announcementDate = announcementDate;
		this.bookID = bookID;
		this.priority = priority;
		this.ownerId = ownerId;
	}

	public Announcement(String description, Date announcementDate,
						int bookID, String priority, int ownerId, String status) {
		this.description = description;
		this.announcementDate = announcementDate;
		this.bookID = bookID;
		this.priority = priority;
		this.ownerId = ownerId;
		this.status = status;
	}

	public Announcement(int announcementID, String description, Date announcementDate,
						int bookID, String priority, int ownerId, String status) {
		super(announcementID);
		this.description = description;
		this.announcementDate = announcementDate;
		this.bookID = bookID;
		this.priority = priority;
		this.ownerId = ownerId;
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

	public int getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getAdmin_id() { return admin_id; }

	public void setAdmin_id(int admin_id) { this.admin_id = admin_id; }

	@Override
	public String toString() {
		return "Announcement{" +
				", description='" + description + '\'' +
				", announcementDate=" + announcementDate +
				", bookID=" + bookID +
				", priority='" + priority + '\'' +
				", ownerId=" + ownerId +
				", status='" + status + '\'' +
				", adminId='" + admin_id + '\'' +
				'}';
	}
}
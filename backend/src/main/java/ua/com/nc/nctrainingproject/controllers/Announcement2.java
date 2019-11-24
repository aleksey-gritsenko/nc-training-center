package ua.com.nc.nctrainingproject.controllers;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class Announcement2 {
    public Announcement2(
            String   description, String announcementDate,
            int bookID, String priority, int ownerId, String status) {
        this.description = description;
        this.announcementDate = announcementDate;
        this.bookID = bookID;
        this.priority = priority;
        this.ownerId = ownerId;
        this.status = status;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAnnouncementDate() {
        return announcementDate;
    }

    public void setAnnouncementDate(String announcementDate) {
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

    private String description;
    private String announcementDate;
    private int bookID;
    private String priority;
    private int ownerId;
    private String status;


}

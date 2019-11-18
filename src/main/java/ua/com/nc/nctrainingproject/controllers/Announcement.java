package ua.com.nc.nctrainingproject.controllers;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class Announcement {
  public Announcement(String title, String description, String date) {
    this.title = title;
    this.description = description;
    this.date = date;
  }
  public Announcement() {

  }

  private String title;
  private String description;
  private String date;

  @Override
  public String toString() {
    return "Announcement{" +
      "title='" + title + '\'' +
      ", description='" + description + '\'' +
      ", date='" + date + '\'' +
      '}';
  }

  public  List<Announcement> method() {
    List<Announcement> list = new ArrayList<>();
    Announcement announcement = new Announcement("title1", "desc1",
      "dddd");
    list.add(announcement);
    Announcement announcement1 = new Announcement("title2", "desc2",
      "2222");
    list.add(announcement1);
    return list;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }
}

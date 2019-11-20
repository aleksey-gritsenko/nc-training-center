package ua.com.nc.nctrainingproject.controllers;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class Announcement2 {
  public Announcement2(String title, String description, String date) {
    this.title = title;
    this.description = description;
    this.date = date;
  }
  public Announcement2() {

  }

  private String title;
  private String description;
  private String date;
private int id;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "Announcement2{" +
      "title='" + title + '\'' +
      ", description='" + description + '\'' +
      ", date='" + date + '\'' +
      ", id=" + id +
      '}';
  }

  public  List<Announcement2> method() {
    List<Announcement2> list = new ArrayList<>();
    Announcement2 announcement = new Announcement2("title1", "desc1",
      "dddd");
    list.add(announcement);
    Announcement2 announcement1 = new Announcement2("title2", "desc2",
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

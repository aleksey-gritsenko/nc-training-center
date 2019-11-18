package ua.com.nc.nctrainingproject.models;

import java.util.List;

public class Book extends Entity {
  private String header;
  private List<Author> authors;
  private String overview;
  private String status;
  private String photo;
  private int fileId;
  private String genre;

  public Book(String header,  String overview, String status, String photo, int fileId) {
    this.header = header;
    this.overview = overview;
    this.status = status;
    this.photo = photo;
    this.fileId = fileId;
  }

  public Book(int id, String header,  String overview, String status, String photo, int fileId, String genre) {
    super(id);
    this.header = header;
    this.overview = overview;
    this.status = status;
    this.photo = photo;
    this.fileId = fileId;
    this.genre = genre;
  }

  public Book() {

  }

  public String getHeader() {
    return header;
  }

  public void setHeader(String header) {
    this.header = header;
  }

  public List<Author> getAuthors() {
    return authors;
  }

  public void setAuthors(List<Author> authors) {
    this.authors = authors;
  }

  public String getOverview() {
    return overview;
  }

  public void setOverview(String overview) {
    this.overview = overview;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getPhotoId() {
    return photo;
  }

  public void setPhotoId(String photo) {
    this.photo = photo;
  }

  public int getFileId() {
    return fileId;
  }

  public void setFileId(int fileId) {
    this.fileId = fileId;
  }

  public void setGenre(String genre) {
    this.genre = genre;
  }

  public String getGenre() {
    return genre;
  }


}
